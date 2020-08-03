package com.example.transction.dao;

/**
 * @author zhoujun
 * @date 2020-07-31 17:07
 */
public interface BookDao {

    Double selectPrice(String bid);

    void updateSt(String bid);

    void updateBalance(String uid,Double price);

}
