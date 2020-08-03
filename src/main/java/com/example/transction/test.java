package com.example.transction;

import com.example.transction.controller.BookController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhoujun
 * @date 2020-07-31 17:57
 */
public class test {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("book.xml");
        BookController bookController = ac.getBean("bookController", BookController.class);
        bookController.buyBook();
    }
}
