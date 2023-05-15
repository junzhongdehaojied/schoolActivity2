package com.junjie.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 陈军杰
 * @since 2023-05-08
 */
@Getter
@Setter
  @ApiModel(value = "Place对象", description = "")
public class Place implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("场地ID")
      @TableId("placeId")
    private Integer placeId;

      @ApiModelProperty("场地名称")
      @TableField("placeName")
    private String placeName;


}
