package com.plan.time.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("基础返回结构")
public class R<T> {
    @ApiModelProperty("状态码")
    private Integer code;
    @ApiModelProperty("错误消息")
    private String errMsg;
    @ApiModelProperty("时间戳")
    private long timestamp = System.currentTimeMillis();
    @ApiModelProperty("响应数据")
    private T data;

    public static <T> R<T> success() {
        R<T> result = new R<T>();
        result.setData(null);
        result.setCode(HttpCode.SUCCESS);
        return result;
    }

    public static <T> R<T> success(T data) {
        R<T> result = new R<T>();
        result.setData(data);
        result.setCode(HttpCode.SUCCESS);
        return result;
    }

    public static <T> R<T> fail(Integer code, String msg) {
        R<T> result = new R<T>();
        result.setCode(code);
        result.setErrMsg(msg);
        result.setErrMsg(msg);
        return result;
    }

    public static <T> R<T> fail(String msg) {
        R<T> result = new R<T>();
        result.setCode(HttpCode.FAIL);
        result.setErrMsg(msg);
        result.setErrMsg(msg);
        return result;
    }

}
