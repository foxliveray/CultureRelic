package com.edu.zju.culture.mbg.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.zju.culture.common.DataGridView;
import com.edu.zju.culture.common.ResultObj;
import com.edu.zju.culture.mbg.entity.LogLogin;
import com.edu.zju.culture.mbg.service.ILogLoginService;
import com.edu.zju.culture.vo.LogLoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author y4oung
 * @since 2020-03-15
 */
@RestController
@RequestMapping("/loginInfo")
public class LogLoginController {
    @Autowired
    private ILogLoginService logLoginService;

    /**
     * 全查询
     */
    @RequestMapping("/loadAllLoginInfo")
    public DataGridView loadAllLoginInfo(LogLoginVo logLoginVo) {
        IPage<LogLogin> page = new Page<>(logLoginVo.getPage(), logLoginVo.getLimit());
        QueryWrapper<LogLogin> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(logLoginVo.getLoginname()), "loginname", logLoginVo.getLoginname());
        queryWrapper.like(StringUtils.isNotBlank(logLoginVo.getLoginip()), "loginip", logLoginVo.getLoginip());
        queryWrapper.ge(logLoginVo.getStartTime() != null, "logintime", logLoginVo.getStartTime());
        queryWrapper.le(logLoginVo.getEndTime() != null, "logintime", logLoginVo.getEndTime());
        queryWrapper.orderByDesc("logintime");
        this.logLoginService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 删除
     */
    @RequestMapping("/deleteLoginfo")
    public ResultObj deleteLoginfo(Integer id) {
        try {
            this.logLoginService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


    /**
     * 批量删除
     */
    @RequestMapping("/batchDeleteLoginfo")
    public ResultObj batchDeleteLoginfo(LogLoginVo loginfoVo) {
        try {
            Collection<Serializable> idList = new ArrayList<Serializable>();
            for (Integer id : loginfoVo.getIds()) {
                idList.add(id);
            }
            this.logLoginService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}

