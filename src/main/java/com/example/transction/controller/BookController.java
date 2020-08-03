package com.example.transction.controller;

import com.example.transction.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author zhoujun
 * @date 2020-07-31 17:07
 */

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    public void buyBook(){
        bookService.buyBook("1","10001");
    }
}
