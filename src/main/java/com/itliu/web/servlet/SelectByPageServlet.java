package com.itliu.web.servlet;


import com.alibaba.fastjson.JSON;
import com.itliu.pojo.Brand;
import com.itliu.pojo.PageBean;
import com.itliu.service.BrandService;
import com.itliu.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectByPageServlet")
public class SelectByPageServlet extends HttpServlet {
    private BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受当前页码和每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //2.调用Service查询
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

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
