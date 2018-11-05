package com.junker.dubbomock.passport.service;

import com.junker.dubbomock.passport.vo.input.UserInfoByTokenInputBean;
import com.junker.dubbomock.passport.vo.output.UserInfoApiOut;

public interface UserInfoDubboService {
    UserInfoApiOut getUserInfoByToken(UserInfoByTokenInputBean var1);
}
