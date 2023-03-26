package com.itliu.service.impl;

import com.itliu.mapper.BrandMapper;
import com.itliu.pojo.Brand;
import com.itliu.pojo.PageBean;
import com.itliu.service.BrandService;
import com.itliu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @create 2023-03-16-8:50
 */
public class BrandServiceImpl implements BrandService {
    //1.创建SqlSessionFactory工厂
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    //查询所有
    public List<Brand> selectAll() {
        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        List<Brand> brands = mapper.selectAll();
        //5.释放资源
        sqlSession.close();
        return brands;
    }

    //添加数据
    public void add(Brand brand) {
        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.add(brand);
        sqlSession.commit();  //提交
        //5.释放资源
        sqlSession.close();
    }

    //批量删除
    public void deleteByIds(int[] ids) {
        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.deleteByIds(ids);
        sqlSession.commit();  //提交
        //5.释放资源
        sqlSession.close();
    }

    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.计算索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;
        //5.查询当前页
        List<Brand> rows = mapper.selectByPage(begin, size);
        //6.查询总记录数
        int toalTotalCount = mapper.selectTotalCount();
        //7.封装PageBean对象
        PageBean<Brand> pageBean = new PageBean<Brand>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(toalTotalCount);
        //8.释放资源
        sqlSession.close();

        return pageBean;
    }

    //分页条件查询
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.计算索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;
        //处理brand的条件，模糊表达式
        String brandName = brand.getBrandName();
        if(brandName != null && brandName.length() >0){
            brand.setBrandName("%" +brandName + "%");
        }
        String companyName = brand.getCompanyName();
        if(companyName != null && companyName.length() >0){
            brand.setCompanyName("%" +companyName + "%");
        }
        //5.查询当前页
        List<Brand> rows = mapper.selectByPageAndCondition(begin, size, brand);
        //6.查询总记录数
        int toalTotalCount = mapper.selectTotalCountByCondition(brand);
        //7.封装PageBean对象
        PageBean<Brand> pageBean = new PageBean<Brand>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(toalTotalCount);
        //8.释放资源
        sqlSession.close();

        return pageBean;
    }

    public void deleteById(int id) {
        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.deleteById(id);
        sqlSession.commit();  //提交
        //5.释放资源
        sqlSession.close();
    }

    public Brand selectById(int id) {
        //2. 获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4. 调用方法
        Brand brand = mapper.selectById(id);
        sqlSession.close();
        return brand;
    }

    public void update(Brand brand) {
        //2. 获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4. 调用方法
        mapper.update(brand);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }
}
