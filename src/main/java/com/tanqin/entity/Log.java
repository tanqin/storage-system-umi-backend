package com.tanqin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author tanqin
 * @since 2023-02-15
 */
@Getter
@Setter
@TableName("t_log")
@ApiModel(value = "Log对象", description = "")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("物品 id")
    private Integer goodsId;

    @ApiModelProperty("出、入库状态(0: 出库; 1: 入库)")
    private Byte type;

    @ApiModelProperty("操作人 id")
    private Integer operatorId;

    @ApiModelProperty("数量")
    private Integer count;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("操作时间")
    private LocalDateTime createTime;
}
