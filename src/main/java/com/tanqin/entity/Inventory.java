package com.tanqin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @ApiModelProperty("种类 id")
    private Integer kindId;

    @ApiModelProperty("商品编号")
    private String goodsCode;

    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("尚帕尼图片地址")
    private String imgUrl;

    @ApiModelProperty("库存数量")
    private Integer quantity;

    @ApiModelProperty("计量单位")
    private String unit;

    @ApiModelProperty("成本价")
    private BigDecimal costPrice;

    @ApiModelProperty("销售价")
    private BigDecimal sellingPrice;

    @ApiModelProperty("状态，包括：0 - 已下架、1 - 已上架、2 - 已售完")
    private Byte status;

    @ApiModelProperty("存放位置")
    private String location;

    @ApiModelProperty("最后更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;
}
