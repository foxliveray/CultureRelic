package com.edu.zju.culture.vo;

import com.edu.zju.culture.mbg.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author y4oung
 * @date 2020/3/18 6:30 PM
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserVo extends User {
    private static final long serialVersionUID = 1L;

    private Integer page = 1;
    private Integer limit = 10;
}
