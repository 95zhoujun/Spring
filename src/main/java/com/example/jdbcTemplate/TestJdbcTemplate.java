package com.example.jdbcTemplate;


import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoujun
 * @date 2020-07-31 11:00
 */
public class TestJdbcTemplate {
    ApplicationContext ac = new ClassPathXmlApplicationContext("jdbc.xml");
    JdbcTemplate jdbc =  ac.getBean("jdbcTemplate", JdbcTemplate.class);

    /**
     * 批量注入
     */
    @Test
    public void testBatchUpdate(){
        System.out.println(jdbc);
        String sql = "insert into emp values(null,?,?,?) ";
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"a1",1,"男"});
        list.add(new Object[]{"a2",2,"女"});
        list.add(new Object[]{"a3",3,"女"});
        list.add(new Object[]{"a4",4,"女"});
        jdbc.batchUpdate(sql,list);
    }

    /**
     * 1）
     *  String sql = "delete from emp where eid in (?)";
     *  String eids = "1,2,3";
     * 不要用通配符去匹配，会只识别出第一条数据
     * 但是拼接会有sql注入
     * 2）
     * String sql1 = "select * from emp where ename like '%?%'"; //不能使用
     * String sql2 = "select * from emp where ename like concat('%',?,'%')";
     * 这种方式控制注入
     */
    @Test
    public void testUpdate(){
        System.out.println(jdbc);
//        String sql = "delete from emp where eid in (?)";
        String eids = "1,2,3";
        String sql = "delete from emp where eid in ("+eids+")";
        String sql1 = "select * from emp where ename like '%?%'"; //不能使用
        String sql2 = "select * from emp where ename like concat('%',?,'%')";
        jdbc.batchUpdate(sql,eids);
    }

    @Test
    public void testQueryForObject(){
//        jdbc.queryForObject(sql,requiredType)用来获取单个的值
//        jdbc.queryForObject(sql,RowMapper)用来获取单条数据
//        String sql = "select eid,ename,age,sex from emp where eid = ?";
//        RowMapper<Emp> rowMapper = new BeanPropertyRowMapper<>(Emp.class);//将列名（字段名或字段名的别名）与属性名映射
//        Emp emp = jdbc.queryForObject(sql,new Object[]{6}, rowMapper);
//        System.out.println(emp);

        String sql = "select count(*) from emp";
        Integer count = jdbc.queryForObject(sql,Integer.class);
        System.out.println(count);

    }

    @Test
    public void  testQuery(){
        String sql = "select eid,ename,age,sex from emp ";
        RowMapper<Emp> rowMapper = new BeanPropertyRowMapper<>(Emp.class);
        List<Emp> list = jdbc.query(sql,rowMapper);
        for(Emp emp : list){
            System.out.println(emp);
        }
    }
}
