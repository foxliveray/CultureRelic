package com.edu.zju.culture.vo;

import com.edu.zju.culture.mbg.entity.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author y4oung
 * @date 2020/3/16 9:21 PM
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleVo extends Role {

    private static final long serialVersionUID = 1L;


    private Integer page = 1;
    private Integer limit = 10;
}
