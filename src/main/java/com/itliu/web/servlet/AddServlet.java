package com.itliu.web.servlet;


import com.alibaba.fastjson.JSON;
import com.itliu.pojo.Brand;
import com.itliu.service.BrandService;
import com.itliu.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
    private BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受品牌数据
        BufferedReader bufferedReader = request.getReader();
        //json字符串
        String params = bufferedReader.readLine();
        //转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        //2.调用service添加
        brandService.add(brand);
        //3.响应成功的表示
        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
