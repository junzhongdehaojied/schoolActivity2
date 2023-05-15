package com.junjie.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 
 * </p>
 *
 * @author 陈军杰
 * @since 2022-11-12
 */
@Getter
@Setter
  @ApiModel(value = "User对象", description = "")
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("用户ID")
      @TableId("userId")
    private String userId;

      @ApiModelProperty("用户姓名")
      @TableField("userName")
    private String userName;

      @ApiModelProperty("用户电话号码")
      @TableField("userPhone")
    private String userPhone;

      @ApiModelProperty("用户账号密码")
      @TableField("userPassword")
    private String userPassword;

      @ApiModelProperty("用户年龄")
      @TableField("userAge")
    private Integer userAge;

      @ApiModelProperty("用户等级，1为注册用户，2为作者，3为管理员")
      @TableField("userLevel")
    private Integer userLevel;

      @ApiModelProperty("用户头像")
      @TableField("userPicture")
    private String userPicture;

      @ApiModelProperty("创建时间")
      @TableField("createTime")
    private LocalDateTime createTime;

}
