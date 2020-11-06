package com.cncs.service.impl;

import com.cncs.dao.IProvinceDao;
import com.cncs.dao.impl.ProvinceDaoImpl;
import com.cncs.domain.Province;
import com.cncs.service.IProvinceService;
import com.cncs.utils.JedisPoolUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ProvinceServiceImpl implements IProvinceService {

    private IProvinceDao provinceDao=new ProvinceDaoImpl();

    @Override
    public List<Province> findAll() {
        return provinceDao.findAll();
    }

    @Override
    public String findAllJson() {
        //先从redis查询
        Jedis jedis = JedisPoolUtils.getJedis();
        String province_json = jedis.get("province");
        //判断redis中数据是否存在
        //不存在，先从mysql查询
        if(province_json == null || province_json.length() == 0){
            System.out.println("从mysql查询");
            List<Province> list = provinceDao.findAll();
            ObjectMapper mapper = new ObjectMapper();
            try {
                province_json = mapper.writeValueAsString(list);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //存入redis
            jedis.set("province",province_json);
        }else {
            //存在，直接从redis返回数据
            System.out.println("从redis缓存查询");
        }
        return province_json;
    }
}
