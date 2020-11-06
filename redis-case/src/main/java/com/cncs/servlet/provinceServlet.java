package com.cncs.servlet;

import com.cncs.domain.Province;
import com.cncs.service.IProvinceService;
import com.cncs.service.impl.ProvinceServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/provinceServlet")
public class provinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取数据
        /*IProvinceService provinceService = new ProvinceServiceImpl();
        List<Province> provinces = provinceService.findAll();
        //将对象序列化
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(provinces);*/

        IProvinceService provinceService = new ProvinceServiceImpl();
        String json = provinceService.findAllJson();

        System.out.println(json);
        //响应
        //设置响应类型
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
