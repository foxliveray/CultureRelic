package com.edu.zju.culture.mbg.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.zju.culture.common.DataGridView;
import com.edu.zju.culture.mbg.entity.Apply;
import com.edu.zju.culture.mbg.entity.Relic;
import com.edu.zju.culture.mbg.service.IApplyService;
import com.edu.zju.culture.mbg.service.IRelicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author y4oung
 * @since 2020-03-16
 */
@RestController
@RequestMapping("/apply")
public class ApplyController {

    @Autowired
    IApplyService iApplyService;

    @Autowired
    IRelicService iRelicService;

    @RequestMapping(value = "/list")
    public DataGridView selectAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int limit) {
        IPage<Apply> apply = new Page<>(page, limit);
        QueryWrapper<Apply> queryWrapper = new QueryWrapper<>();
        this.iApplyService.page(apply, queryWrapper);
        return new DataGridView(apply.getTotal(), apply.getRecords());
    }

    @RequestMapping(value = "/detail")
    public DataGridView detail(@RequestParam(value = "applyId") Long applyId) {
        Map<String, Object> data = new HashMap<>();
        Apply apply = iApplyService.getById(applyId);
        Relic relic = iRelicService.getById(apply.getRelicId());
        data.put("applyContent", apply.getApplyContent());
        data.put("fromId", apply.getFromId());
        data.put("relicName", relic.getRelicName());
        data.put("govNum", relic.getGovNum());
        data.put("relicId", relic.getRelicId());
        data.put("description", relic.getRelicDescribe());
        return new DataGridView(data);
    }

    @RequestMapping(value = "/handleException")
    public DataGridView handleException(@RequestParam(value = "applyId") Long applyId, @RequestParam(value = "applyResponse") String applyResponse) {
        Apply apply = iApplyService.getById(applyId);
        apply.setApplyResponse(applyResponse);
        iApplyService.updateById(apply);
        return new DataGridView();
    }

}

