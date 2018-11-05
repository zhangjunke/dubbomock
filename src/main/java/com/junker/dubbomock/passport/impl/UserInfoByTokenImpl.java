package com.junker.dubbomock.passport.impl;

import com.junker.dubbomock.passport.data.UserInfoByTokenData;
import com.junker.dubbomock.passport.service.UserInfoDubboService;
import com.junker.dubbomock.passport.util.MockMatchingUtil;
import com.junker.dubbomock.passport.vo.input.UserInfoByTokenInputBean;
import com.junker.dubbomock.passport.vo.output.UserInfoApiOut;

import java.util.ArrayList;

public class UserInfoByTokenImpl implements UserInfoDubboService {
    public UserInfoApiOut getUserInfoByToken(UserInfoByTokenInputBean var1) {
        UserInfoByTokenData uibd=new UserInfoByTokenData();

        String token=var1.getAccessToken();
        String userId=var1.getApiUserId();
        String channel=var1.getChannel();
        String mac=var1.getMac();

        uibd.setToken(token);
        uibd.setUserId(userId);
        uibd.setChannel(channel);
        uibd.setMac(mac);

        String conditionS="accessToken="+token+"&API_USER_ID="+userId+"&CHANNEL="+channel+"&mac="+mac;
        MockMatchingUtil mmu=new MockMatchingUtil();
        ArrayList<String> matching= mmu.mockMatching("UserInfoDubboService",conditionS);
        System.out.println("matching:"+matching);

        UserInfoApiOut uiao=new UserInfoApiOut();
        if(matching.size()<2){
            uiao.setMsg("未匹配到有效mock条件");
            uiao.setCode("1111111");
            return uiao;
        }
        String timeout=matching.get(0);
        String responseMsg=matching.get(1).replace("{","").replace("}","");
        String code=responseMsg.split(",")[0].split("=")[1];
        String msg=responseMsg.split(",")[1].split("=")[1];

        uiao.setCode(code);
        uiao.setMsg(msg);
        try {
            Thread.sleep(Long.parseLong(timeout));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return uiao;
    }

}
