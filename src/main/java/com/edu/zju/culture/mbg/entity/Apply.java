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
public class Apply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文物上链申请ID
     */
    @TableId(value = "apply_id", type = IdType.AUTO)
    private Long applyId;

    /**
     * 文物ID
     */
    private Long relicId;

    /**
     * 文物上链申请时间
     */
    private LocalDateTime applyDate;

    /**
     * 发起人ID
     */
    private Long fromId;

    /**
     * 是否已审核
     */
    private Integer checkStatus;

    /**
     * 审核意见
     */
    private String applyResponse;


}
