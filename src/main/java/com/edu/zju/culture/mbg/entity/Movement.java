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
public class Movement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流转ID
     */
    @TableId(value = "movement_id", type = IdType.AUTO)
    private Long movementId;

    /**
     * 流转说明
     */
    private String explanation;

    /**
     * 流转类型（交易、捐赠等）
     */
    private String moveType;

    /**
     * 流转发生时间
     */
    private LocalDateTime moveDate;

    /**
     * 文物ID
     */
    private Long relicId;

    /**
     * 流转发起人ID
     */
    private Long fromId;

    /**
     * 流转接受人ID
     */
    private Long toId;

    /**
     * 是否已审核
     */
    private Integer checkStatus;

    /**
     * 审核意见
     */
    private String movementResponse;


}
