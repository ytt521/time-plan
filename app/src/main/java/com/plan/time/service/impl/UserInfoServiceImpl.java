package com.plan.time.service.impl;

import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Opt;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plan.time.constants.UserInfoConstants;
import com.plan.time.entity.UserInfo;
import com.plan.time.exception.AccountException;
import com.plan.time.mapper.UserInfoMapper;
import com.plan.time.request.user.UserLoginDTO;
import com.plan.time.result.R;
import com.plan.time.service.UserInfoService;
import com.plan.time.utils.AESUtil;
import com.plan.time.utils.IpUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private HttpServletRequest request;

    @Override
    @SneakyThrows
    public R<String> login(UserLoginDTO userLoginDTO) {
        UserInfo userInfo = Opt.ofNullable(getOne(Wrappers.<UserInfo>lambdaQuery()
                        .select(UserInfo::getId, UserInfo::getPassword,
                                UserInfo::getDisabled, UserInfo::getSecretKey, UserInfo::getUserName, UserInfo::getNickName)
                        .eq(UserInfo::getUserName, userLoginDTO.getUserName())))
                .orElseThrow(AccountException::new, UserInfoConstants.LOGIN_ERROR);
        if (Objects.equals(userInfo.getDisabled(), UserInfoConstants.ACCOUNT_DISABLED)) {
            return R.fail(UserInfoConstants.ACCOUNT_DISABLED_ERROR);
        }
        String secretKey = userInfo.getSecretKey();
        if (!AESUtil.encrypt(userLoginDTO.getPassword(), secretKey.getBytes()).equals(userInfo.getPassword())) {
            return R.fail(UserInfoConstants.LOGIN_ERROR);
        }
        // String ipAddress = IpUtil.getIpAddress(request);
        // String ipSource = IpUtil.getIpSource(ipAddress);
        StpUtil.login(userInfo.getId(), SaLoginConfig
                .setExtra("userName", userInfo.getUserName())
                .setExtra("nickName", userInfo.getNickName())
                .setExtra("id", userInfo.getId()));
        return R.success(StpUtil.getTokenValue());
    }
}
