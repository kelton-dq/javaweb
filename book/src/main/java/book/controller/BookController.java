/**
 * Copyright (C), 2020-2022, XDU
 * FileName: BookController
 * Author: Dingq
 * Date: 2022/4/27 19:23
 * Description:
 */
package book.controller;

import book.pojo.Book;
import book.service.BookService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class BookController {
    private BookService bookService;

    public String index(HttpSession session){
        List<Book> bookList = bookService.getBookList();
        session.setAttribute("bookList", bookList);
        return "index";
    }
}