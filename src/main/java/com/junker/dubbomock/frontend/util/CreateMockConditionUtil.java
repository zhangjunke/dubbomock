package com.junker.dubbomock.frontend.util;

import com.junker.dubbomock.frontend.dao.SearchMockConfDAO;
import java.util.Properties;
import java.sql.SQLException;

public class CreateMockConditionUtil {
    public static int createCondtion(String mockResponseMsg,String nameId,String mockCondition){
        int result=0;
        SearchMockConfDAO smf = new SearchMockConfDAO();
        String detailId="";
        Properties initProp = new Properties(System.getProperties());
        System.out.println("��ǰϵͳ����:" + initProp.getProperty("file.encoding"));
        System.out.println("��ǰϵͳ����:" + initProp.getProperty("user.language"));
        String conName=mockCondition.split("=")[0];
        System.out.println("ȫ����Ч" == conName);
        System.out.println("test=" + conName);
        if("ȫ����Ч".equals(conName)){
            mockCondition="ALL";
        }
        if(conName.equals("�ݲ�����")){
            return 1;
        }
        System.out.println("mockmsg:"+mockResponseMsg);
        System.out.println("mockCondition:"+mockCondition);
        System.out.println("name:"+nameId);
        try {
            detailId=smf.mockDetailIdSearch(mockResponseMsg,nameId);
            result =smf.mockConditionCreate(detailId,mockCondition);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  result;

    }
}
