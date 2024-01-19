package com.plan.time.config;

import com.plan.time.exception.AccountException;
import com.plan.time.result.HttpCode;
import com.plan.time.result.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器
 *
 * @author 小樱
 * @date 2024/01/20
 */
@ControllerAdvice
public class GlobalExceptionConfig {

    @ResponseBody
    @ExceptionHandler
    public R<?> handlerException(Exception e) {
        R<?> result = new R<>();
        if (e instanceof AccountException) {
            result = R.fail(HttpCode.NOT_LOGIN, e.getMessage());
        }
        return result;
    }
}
