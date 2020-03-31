package com.edu.zju.culture.vo;

import com.edu.zju.culture.mbg.entity.Notice;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author y4oung
 * @date 2020/3/31 1:21 PM
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NoticeVo extends Notice {
    private static final long serialVersionUID = 1L;

    private Integer page = 1;
    private Integer limit = 10;

    private int[] ids;  //接受多个id

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

}
