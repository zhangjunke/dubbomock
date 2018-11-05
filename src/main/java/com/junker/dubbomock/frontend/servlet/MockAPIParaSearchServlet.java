package com.junker.dubbomock.frontend.servlet;

import com.junker.dubbomock.frontend.dao.SearchMockConfDAO;
import com.junker.dubbomock.frontend.util.RegexUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(name = "MockAPIParaSearchServlet")
public class MockAPIParaSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String uri = request.getRequestURI();
        RegexUtil ru=new RegexUtil();
        String APIname=ru.getMatcher("API=(.*)",uri);
        SearchMockConfDAO smcd = new SearchMockConfDAO();
        HashMap<String,String> result=new HashMap<String,String>();
        try {
            result=smcd.mockAPIParaSearch(APIname);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String resultJsonString="{\n" +
                "    \"list\": [\n" +
                "        {\n" +
                "            \"input\":\"" +result.get("input_parameters")+"\"" +
                "        },\n" +
                "        {\n" +
                "            \"output\":\"" +result.get("output_parameters")+"\"" +
                "        }\n" +
                "    ]\n" +
                "}";

        PrintWriter out = response.getWriter();
        out.print(resultJsonString);
        out.flush();
        out.close();
    }
}
