package com.example.transction.dao.impl;

import com.example.transction.dao.BookDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author zhoujun
 * @date 2020-07-31 17:09
 */

@Repository
public class BookDaoIml implements BookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Double selectPrice(String bid) {
        String sql = "select price from book where bid = ? ";
        Double price = jdbcTemplate.queryForObject(sql,new Object[]{bid},Double.class);
        return price;
    }

    @Override
    public void updateSt(String bid) {
        String sql = "select st from stock where sid = ? ";
        Integer stock = jdbcTemplate.queryForObject(sql,new Object[]{bid},Integer.class);
        if(stock>0){
            jdbcTemplate.update("update stock set st = ? where sid = ?",new Object[]{stock-1,bid});
        }else {
            throw new RuntimeException();
        }

    }

    @Override
    public void updateBalance(String uid, Double price) {
        Double balance = jdbcTemplate.queryForObject("select balance from money where uid = ?",new Object[]{uid},Double.class);
        if(balance<price){
            throw new RuntimeException();
        }else {
            jdbcTemplate.update("update money set balance = balance - ?  where uid = ?",price,uid);
        }

    }
}
