package com.tanqin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author tanqin
 * @since 2023-02-07
 */
@Getter
@Setter
@TableName("t_user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    @TableField(select = false)
    private String password;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("性别(0: 女; 1: 男)")
    private Byte sex;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("用户权限(0: 超级管理员; 1: 管理员; 2: 普通账号)")
    private Byte roleId;

    @ApiModelProperty("账号是否有效(0: 无效; 1: 有效)")
    private Byte isValid;
}
