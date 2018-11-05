package com.junker.dubbomock.frontend.servlet;

import com.junker.dubbomock.frontend.util.DubboStartUtil;
import sun.text.resources.FormatData;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DubboStartServlet")
public class DubboStartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DubboStartUtil dsu=new DubboStartUtil();
        dsu.startDubbo();
    }
}