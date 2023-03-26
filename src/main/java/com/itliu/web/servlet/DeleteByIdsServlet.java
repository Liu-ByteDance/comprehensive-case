package com.itliu.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itliu.pojo.Brand;
import com.itliu.service.BrandService;
import com.itliu.service.impl.BrandServiceImpl;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/deleteByIdsServlet")
public class DeleteByIdsServlet extends HttpServlet {
    private BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受数组数据
        BufferedReader bufferedReader = request.getReader();
        //json字符串
        String params = bufferedReader.readLine();
        //转为int数组
        int[] ids = JSON.parseObject(params, int[].class);
        //2.调用service添加
        brandService.deleteByIds(ids);
        //3.响应成功的表示
        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
