package com.cncs.dao.impl;

import com.cncs.dao.IProvinceDao;
import com.cncs.domain.Province;
import com.cncs.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProvinceDaoImpl implements IProvinceDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Province> findAll() {
        return jdbcTemplate.query("select * from province",new BeanPropertyRowMapper<Province>(Province.class));
    }
}
