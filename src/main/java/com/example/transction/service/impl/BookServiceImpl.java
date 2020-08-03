package com.example.transction.service.impl;

import com.example.transction.dao.BookDao;
import com.example.transction.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhoujun
 * @date 2020-07-31 17:08
 */

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public void buyBook(String bid, String uid) {
        Double price = bookDao.selectPrice(bid);
        bookDao.updateBalance(uid,price);
        bookDao.updateSt(bid);
    }
}
