package com.edu.zju.culture.mbg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author y4oung
 * @since 2020-03-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 交易ID
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;

    /**
     * 交易金额
     */
    private Integer orderValue;

    /**
     * 交易日期
     */
    private LocalDateTime orderDate;

    /**
     * 交易状态
     */
    private Integer orderStatus;

    /**
     * 文物ID
     */
    private Long relicId;

    /**
     * 买方ID
     */
    private Long buyerId;

    /**
     * 卖方ID
     */
    private Long sellerId;

    /**
     * 是否已审核
     */
    private String checkStatus;

    /**
     * 审核意见
     */
    private String orderResponse;

    /**
     * 交易是否已上链
     */


}
