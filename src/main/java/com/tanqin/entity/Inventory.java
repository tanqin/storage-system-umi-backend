package com.tanqin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author tanqin
 * @since 2023-02-18
 */
@Getter
@Setter
@TableName("t_inventory")
@ApiModel(value = "Inventory对象", description = "")
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("库存ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("商品编号")
    private String itemNumber;

    @ApiModelProperty("商品名称")
    private String itemName;

    @ApiModelProperty("库存数量")
    private Integer quantity;

    @ApiModelProperty("计量单位")
    private String unit;

    @ApiModelProperty("成本价")
    private BigDecimal costPrice;

    @ApiModelProperty("销售价")
    private BigDecimal sellingPrice;

    @ApiModelProperty("状态，包括：0 - 已下架、1 - 已上架2 - 已售完")
    private Byte status;

    @ApiModelProperty("存放位置")
    private String location;

    @ApiModelProperty("最后更新时间")
    private LocalDateTime lastUpdated;
}
