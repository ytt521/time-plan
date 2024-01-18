package com.plan.time.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.plan.time.entity.UserInfo;
import com.plan.time.request.user.UserLoginDTO;
import com.plan.time.result.R;

public interface UserInfoService extends IService<UserInfo> {

    /**
     * 用户登录
     *
     * @param userLoginDTO 账号信息
     * @return {@link R}<{@link String}>
     */
    R<String> login(UserLoginDTO userLoginDTO);
}
