package com.book.servlet.pages;

import com.book.entity.User;
import com.book.service.BookService;
import com.book.service.StudentService;
import com.book.service.impl.BookServiceImpl;
import com.book.service.impl.StudentServiceImpl;
import com.book.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
    BookService bookService;
    @Override
    public void init() throws ServletException {
        bookService = new BookServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Context context = new Context();
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("nickname",user.getNickname());
        context.setVariable("book_list",bookService.getBookList().keySet());
        context.setVariable("book_list_status",new ArrayList<>(bookService.getBookList().values()));
        ThymeleafUtil.process("books.html",context,resp.getWriter());
    }
}
