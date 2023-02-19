package com.tanqin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2023-02-14
 */
@Getter
@Setter
@TableName("t_kind")
@ApiModel(value = "Kind对象", description = "")
public class Kind implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("种类名称")
    private String name;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("是否生效(0: 关闭, 1: 开启)")
    private Boolean isValid;
}
