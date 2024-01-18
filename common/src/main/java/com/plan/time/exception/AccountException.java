package com.plan.time.exception;

/**
 * 账户信息异常
 *
 * @author 小樱
 * @date 2024/01/18
 */
public class AccountException extends RuntimeException {
    public AccountException() {
    }

    public AccountException(String message) {
        super(message);
    }
}
