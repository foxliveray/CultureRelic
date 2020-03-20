package com.edu.zju.culture.mbg.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.zju.culture.common.DataGridView;
import com.edu.zju.culture.common.ResultObj;
import com.edu.zju.culture.fabric.FabricHelper;
import com.edu.zju.culture.mbg.entity.ExitEntry;
import com.edu.zju.culture.mbg.entity.Relic;
import com.edu.zju.culture.mbg.service.IExitEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author y4oung
 * @since 2020-03-16
 */
@RestController
@RequestMapping("/exit-entry")
public class ExitEntryController {

    @Autowired
    IExitEntryService iExitEntryService;

    @RequestMapping("/list")
    public DataGridView selectAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int limit) {
        IPage<ExitEntry> exitEntryIPage = new Page<>(page, limit);
        QueryWrapper<ExitEntry> queryWrapper = new QueryWrapper<>();
        this.iExitEntryService.page(exitEntryIPage, queryWrapper);
        return new DataGridView(exitEntryIPage.getPages(), exitEntryIPage.getRecords());
    }

    @RequestMapping(value = "/insertExitEntry")
    public ResultObj insertExitEntry(ExitEntry exitEntry) {
        exitEntry.setExitEntryCheckStatus(0);
        exitEntry.setExitEntryCustomsStatus(0);
        exitEntry.setExitEntryBlockChainStatus(0L);
        iExitEntryService.save(exitEntry);
        return ResultObj.ADD_SUCCESS;
    }

    @RequestMapping(value = "/govCheckExitEntry")
    public void govCheckExitEntry(@RequestParam(value = "exitEntryId")Long exitEntryId,@RequestParam(value = "exitEntryCheckStatus")Integer exitEntryCheckStatus,@RequestParam(value = "exitEntryResponse")String exitEntryResponse) throws IOException {
        ExitEntry exitEntry=new ExitEntry();
        exitEntry.setExitEntryId(exitEntryId);
        exitEntry.setExitEntryCheckStatus(exitEntryCheckStatus);
        exitEntry.setExitEntryResponse(exitEntryResponse);
        iExitEntryService.updateById(exitEntry);
    }


}

