package com.junker.dubbomock.frontend.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import com.junker.dubbomock.frontend.dao.SearchMockConfDAO;
import com.junker.dubbomock.frontend.util.CreateMockConditionUtil;
import com.junker.dubbomock.frontend.util.JsonFormatCheck;

@WebServlet(name = "MockDetailCreateServlet")
public class MockDetailCreateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        JsonFormatCheck jfc=new JsonFormatCheck();

        String createResult="0";
        String mockType=request.getParameter("mockType");
        String mockCaseName=request.getParameter("mockCaseName");
        String mock_timeout=request.getParameter("mock_timeout");
        String mockResponseMsg=request.getParameter("mockMsg");
        String mockAPI=request.getParameter("mockAPI");
        String name=request.getParameter("name");
        String mockCondition=request.getParameter("mockCondition");

        //报文格式：A=233|B=234,转换为{A=233,B=234}
        mockResponseMsg=mockResponseMsg.substring(0,mockResponseMsg.length()-1);
        mockResponseMsg=mockResponseMsg.replace("|",",");
        mockResponseMsg="{"+mockResponseMsg+"}";

        SearchMockConfDAO smf = new SearchMockConfDAO();
        try {
            String APIId=smf.mockAPIidSearch(mockAPI);
            String nameId=smf.useridSearch(name);
            smf.mockDetailCreate(APIId,mockType,mockCaseName,mock_timeout,mockResponseMsg,nameId);
            CreateMockConditionUtil cmcu=new CreateMockConditionUtil();
            cmcu.createCondtion(mockResponseMsg,nameId,mockCondition);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.print(createResult);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
