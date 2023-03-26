package com.itliu.service;

import com.itliu.pojo.Brand;
import com.itliu.pojo.PageBean;

import java.util.List;

/**
 * @create 2023-03-16-8:50
 */
public interface BrandService {

    //查询
    List<Brand> selectAll();

    //添加
    void add(Brand brand);

    //批量删除
    void deleteByIds(int[] ids);

    //当前页码和每页查询条数
    PageBean<Brand> selectByPage(int currentPage,int pageSize);

    //分页条件查询
    PageBean<Brand> selectByPageAndCondition(int currentPage,int pageSize,Brand brand);

    //删除用户
    void deleteById(int id);

    //回显数据
    Brand selectById(int id);

    //修改数据
    void update(Brand brand);
}
