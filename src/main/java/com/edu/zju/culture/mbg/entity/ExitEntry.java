package com.edu.zju.culture.mbg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

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
public class ExitEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 出入境记录ID
     */
    @TableId(value = "exit_entry_id", type = IdType.AUTO)
    private Long exitEntryId;

    /**
     * 发起人ID
     */
    private Long fromId;

    /**
     * 接受人ID
     */
    private Long toId;

    /**
     * 文物ID
     */
    private Long relicId;

    /**
     * 出关地点
     */
    private String origin;

    /**
     * 目的地
     */
    private String destination;

    /**
     * 政府是否已审核
     */
    private Integer exitEntryCheckStatus;

    /**
     * 政府审核意见
     */
    private String exitEntryResponse;

    /**
     * 海关是否已审核
     */
    private Integer exitEntryCustomsStatus;

    /**
     * 海关审核意见
     */
    private String exitEntryCustomsResponse;
    /**
     *  表示出入境信息是否已上链
     */
    private Long exitEntryBlockChainStatus;


}
