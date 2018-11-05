package com.junker.dubbomock.frontend.servlet;
import com.junker.dubbomock.frontend.dao.SearchMockConfDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "ModifySubmitServlet")
public class ModifySubmitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String mockType=request.getParameter("mockType");
        String detailid=request.getParameter("detailid");
        String caseName=request.getParameter("mockCaseName");
        String time=request.getParameter("mock_timeout");
        String msg=request.getParameter("mockMsg");
        String mockCondition=request.getParameter("mockCondition");
        String oldCondition=request.getParameter("oldCondition");
        //报文格式：A=233|B=234,转换为{A=233,B=234}
        msg=msg.substring(0,msg.length()-1);
        msg=msg.replace("|",",");
        msg="{"+msg+"}";

        System.out.println("modifySubmit_detailid:"+detailid);
        System.out.println("modifySubmit_caseName:"+caseName);
        System.out.println("modifySubmit_mockCondition:"+mockCondition);
        System.out.println("modifySubmit_time:"+time);
        System.out.println("modifySubmit_msg:"+msg);
        System.out.println("modifySubmit_oldCondition:"+oldCondition);
        SearchMockConfDAO smf =new SearchMockConfDAO();
        String modifyResult="";
        try {
            String conditionId=smf.mockconditionidSearch(detailid,oldCondition);
            System.out.println("modifySubmit_conditionId:"+conditionId);
            smf.mockDetailUpdate(mockType, caseName, time, msg,detailid);
            smf.mockConditionUpdate(conditionId,mockCondition);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        modifyResult="0";
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.println(modifyResult);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
