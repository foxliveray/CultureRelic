package com.edu.zju.culture.vo;

import com.edu.zju.culture.mbg.entity.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author y4oung
 * @date 2020/3/15 12:31 PM
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PermissionVo extends Permission {
    private static final long serialVersionUID = 1L;

    private Integer page = 1;
    private Integer limit = 10;

}
