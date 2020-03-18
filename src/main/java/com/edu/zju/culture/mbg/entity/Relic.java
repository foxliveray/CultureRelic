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
 * @since 2020-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Relic implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 文物ID
     */
      @TableId(value = "relic_id", type = IdType.AUTO)
    private Long relicId;

    /**
     * 国家认证编号
     */
    private String govNum;

    /**
     * 文物名称
     */
    private String relicName;

    /**
     * 文物描述
     */
    private String relicDescribe;

    /**
     * 文物图片地址
     */
    private String picture;

    /**
     * 是否已鉴定
     */
    private Integer identityStatus;

    /**
     * 鉴定结果
     */
    private Integer relicStatus;

    /**
     * 鉴定人ID
     */
    private Long identityId;

    /**
     * 文物所有人ID(根据所有人ID查询部门)
     */
    private Long ownerId;


}
