package com.edu.zju.culture.vo;

import com.edu.zju.culture.mbg.entity.LogLogin;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author y4oung
 * @date 2020/3/15 3:26 PM
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LogLoginVo extends LogLogin {
    private static final long serialVersionUID = 1L;

    private Integer page;
    private Integer limit;

    private Integer[] ids; //接受多个删除id

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
