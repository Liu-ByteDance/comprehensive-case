package com.itliu.web.servlet;


import com.alibaba.fastjson.JSON;
import com.itliu.pojo.Brand;
import com.itliu.pojo.PageBean;
import com.itliu.service.BrandService;
import com.itliu.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

//分页条件查询
@WebServlet("/selectByPageAndConditionServlet")
public class SelectByPageAndConditionServlet extends HttpServlet {
    private BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受当前页码和每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //获取对应的查询条件对象
        //1.接受数组数据
        BufferedReader bufferedReader = request.getReader();
        //json字符串
        String params = bufferedReader.readLine();
        //转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //2.调用Service查询
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize,brand);

        //3.转成JSON
        String s = JSON.toJSONString(pageBean);
        //4.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
