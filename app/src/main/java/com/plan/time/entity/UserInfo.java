package com.plan.time.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("用户对象")
@TableName("t_user_info")
public class UserInfo {
    @ApiModelProperty("用户id")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty("账号")
    private String userName;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("秘钥")
    private String secretKey;
    @ApiModelProperty("登录类型")
    private Integer loginType;
    @ApiModelProperty("ip地址")
    private String ipAddress;
    @ApiModelProperty("ip来源")
    private String ipSource;
    @ApiModelProperty("是否被禁用")
    private Integer disabled;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新时间")
    private Date updateTime;
    @ApiModelProperty("最后登录时间")
    private Date lastLoginTime;
}
