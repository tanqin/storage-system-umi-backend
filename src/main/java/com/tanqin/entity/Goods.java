package com.tanqin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
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
 * @since 2023-02-14
 */
@Getter
@Setter
@TableName("t_goods")
@ApiModel(value = "Goods对象", description = "")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("所属仓库 id")
    private Integer storageId;

    @ApiModelProperty("所属种类 id")
    private Integer kindId;

    @ApiModelProperty("物品名称")
    private String name;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("物品图片地址")
    private String imgUrl;

    @ApiModelProperty("物品单价(单位: 元)")
    private BigDecimal price;

    @ApiModelProperty("物品数量")
    private Integer count;

    @ApiModelProperty("是否生效(0: 关闭, 1: 开启)")
    private Boolean isValid;
}
