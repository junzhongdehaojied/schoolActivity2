package com.junjie.demo.controller.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * 接受登录请求参数
 */

@Data
public class UserDTO {

    @TableField("userId")
    private String userId;
    @TableField("userName")
    private String userName;
    @TableField("userPassword")
    private String userPassword;
    @TableField("userPhone")
    private String userPhone;
    @TableField("userAge")
    private String userAge;
    @TableField("userPicture")
    private String userPicture;
    @TableField("userLevel")
    private String userLevel;


}
