package com.junker.dubbomock.passport.util;

import com.junker.dubbomock.frontend.dao.SearchMockConfDAO;
import com.junker.dubbomock.passport.data.UserInfoByTokenData;

import java.sql.SQLException;
import java.util.ArrayList;

public class MockMatchingUtil {
    public static ArrayList<String> mockMatching(String serviceName,String conditionS){
        SearchMockConfDAO smcf=new SearchMockConfDAO();
        UserInfoByTokenData uib=new UserInfoByTokenData();
        ArrayList<String> matching=new ArrayList<String>();
        try {
            matching=smcf.mockSettingMatch(conditionS,serviceName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(null!=matching){
            return matching;
        }else{
            matching.add("");
            return matching;
        }

    }

}
