package com.itliu.mapper;

import com.itliu.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {
    //查询
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    //添加
    @Insert("insert into tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);

    //批量删除
    void deleteByIds(@Param("ids") int[] ids);

    //分页查询
    @Select("select * from tb_brand limit #{begin},#{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin")int begin,@Param("size")int size);

    //总条数
    @Select("select count(*) from tb_brand")
    int selectTotalCount();

    //分页条件查询
    List<Brand> selectByPageAndCondition(@Param("begin")int begin,@Param("size")int size,@Param("brand")Brand brand);

    //根据条件查询总记录数
    int selectTotalCountByCondition(Brand brand);

    //删除信息
    @Delete("delete from tb_brand where id = #{id}")
    @ResultMap("brandResultMap")
    void deleteById(int id);

    //回显数据
    @Select("select * from tb_brand where id = #{id}")
    @ResultMap("brandResultMap")
    Brand selectById(int id);

    //修改数据
    @Update("update tb_brand set brand_name = #{brandName},company_name = #{companyName},ordered = #{ordered},description = #{description},status = #{status} where id = #{id}")
    void update(Brand brand);
}
