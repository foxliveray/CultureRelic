package com.edu.zju.culture.mbg.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.zju.culture.common.DataGridView;
import com.edu.zju.culture.common.ResultObj;
import com.edu.zju.culture.fabric.FabricHelper;
import com.edu.zju.culture.mbg.entity.ExitEntry;
import com.edu.zju.culture.mbg.entity.Relic;
import com.edu.zju.culture.mbg.entity.Trade;
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
        return new DataGridView(exitEntryIPage.getTotal(), exitEntryIPage.getRecords());
    }

    @RequestMapping(value = "/insertExitEntry")
    public ResultObj insertExitEntry(ExitEntry exitEntry) {
        exitEntry.setExitEntryCheckStatus(0);
        exitEntry.setExitEntryCustomsStatus(0);
        exitEntry.setExitEntryBlockChainStatus(0L);
        iExitEntryService.save(exitEntry);
        return ResultObj.ADD_SUCCESS;
    }

    /**
     * 海关授权
     */
    @RequestMapping(value = "/checkExitEntry")
    public void checkExitEntry(@RequestParam(value = "exitEntryId") Long exitEntryId, @RequestParam("fromId")Long fromId,@RequestParam("toId")Long toId,@RequestParam("relicId")Long relicId,@RequestParam("origin")String origin,@RequestParam("destination")String destination,@RequestParam("exitEntryCheckStatus")Integer exitEntryCheckStatus,@RequestParam("exitEntryResponse")String exitEntryResponse,@RequestParam(value = "exitEntryCustomsStatus") Integer exitEntryCustomsStatus, @RequestParam(value = "exitEntryCustomsResponse") String exitEntryCustomsResponse,@RequestParam("exitEntryBlockChainStatus")Long exitEntryBlockChainStatus) throws IOException {
        ExitEntry exitEntry = new ExitEntry();
        exitEntry.setExitEntryId(exitEntryId);
        exitEntry.setExitEntryCustomsStatus(exitEntryCustomsStatus);
        exitEntry.setExitEntryCustomsResponse(exitEntryCustomsResponse);
        exitEntry.setExitEntryBlockChainStatus(exitEntryBlockChainStatus);
        iExitEntryService.updateById(exitEntry);
        exitEntry.setFromId(fromId);
        exitEntry.setToId(toId);
        exitEntry.setRelicId(relicId);
        exitEntry.setOrigin(origin);
        exitEntry.setDestination(destination);
        exitEntry.setExitEntryCheckStatus(exitEntryCheckStatus);
        exitEntry.setExitEntryResponse(exitEntryResponse);
        if(exitEntry.getExitEntryBlockChainStatus()==1){
            FabricHelper fabricHelper=new FabricHelper();
            fabricHelper.init();
            fabricHelper.addExitEntry(exitEntry);
        }

    }

    /**
     * 政府授权
     */
    @RequestMapping(value = "/govCheckExitEntry")
    public void govCheckExitEntry(@RequestParam(value = "exitEntryId") Long exitEntryId, @RequestParam(value = "exitEntryCheckStatus") Integer exitEntryCheckStatus, @RequestParam(value = "exitEntryResponse") String exitEntryResponse) throws IOException {
        ExitEntry exitEntry = new ExitEntry();
        exitEntry.setExitEntryId(exitEntryId);
        exitEntry.setExitEntryCheckStatus(exitEntryCheckStatus);
        exitEntry.setExitEntryResponse(exitEntryResponse);
        iExitEntryService.updateById(exitEntry);
    }

    //与链上的出入境信息比较
    @RequestMapping(value = "/compareExitEntry")
    public String compareExitEntrywithBlock(@RequestParam(value = "exitEntryId") Long exitEntryId, @RequestParam("fromId")Long fromId,@RequestParam("toId")Long toId,@RequestParam("relicId")Long relicId,@RequestParam("origin")String origin,@RequestParam("destination")String destination,@RequestParam("exitEntryCheckStatus")Integer exitEntryCheckStatus,@RequestParam("exitEntryResponse")String exitEntryResponse,@RequestParam(value = "exitEntryCustomsStatus") Integer exitEntryCustomsStatus, @RequestParam(value = "exitEntryCustomsResponse") String exitEntryCustomsResponse,@RequestParam("exitEntryBlockChainStatus")Long exitEntryBlockChainStatus) throws IOException {
        ExitEntry exitEntry=new ExitEntry();
        FabricHelper fabricHelper=new FabricHelper();
        fabricHelper.init();
        exitEntry=fabricHelper.getExitEntry(exitEntryId);
        if(exitEntry.getFromId()!=fromId){
            String back="警告！出入境流转发起人信息出现异常，链上信息为：\""+exitEntry.getFromId()+"\"。您可以提交异常处理申请向政府进行报告！";
            return back;
        }
        if(exitEntry.getToId()!=toId){
            String back="警告！出入境流转接受人信息出现异常，链上信息为：\""+exitEntry.getToId()+"\"。您可以提交异常处理申请向政府进行报告！";
            return back;
        }

        if(!exitEntry.getOrigin().equals(origin)){
            String back="警告！出关地点信息出现异常，链上信息为：\""+exitEntry.getOrigin()+"\"。您可以提交异常处理申请向政府进行报告！";
            return back;
        }
        if(!exitEntry.getDestination().equals(destination)){
            String back="警告！出入境流转目的地出现异常，链上信息为：\""+exitEntry.getDestination()+"\"。您可以提交异常处理申请向政府进行报告！";
            return back;
        }
        if(exitEntry.getExitEntryCheckStatus()!=exitEntryCheckStatus){
            String back="警告！政府审核状态出现异常，链上信息为：\""+exitEntry.getExitEntryCheckStatus()+"\"。您可以提交异常处理申请向政府进行报告！";
            return back;
        }
        if(!exitEntry.getExitEntryResponse().equals(exitEntryResponse)){
            String back="警告！政府审核意见出现异常，链上信息为：\""+exitEntry.getExitEntryResponse()+"\"。您可以提交异常处理申请向政府进行报告！";
            return back;
        }
        if(exitEntry.getExitEntryCustomsStatus()!=exitEntryCustomsStatus){
            String back="警告！海关审核状态出现异常，链上信息为：\""+exitEntry.getExitEntryCheckStatus()+"\"。您可以提交异常处理申请向政府进行报告！";
            return back;
        }
        if(!exitEntry.getExitEntryCustomsResponse().equals(exitEntryCustomsResponse)){
            String back="警告！海关审核意见出现异常，链上信息为：\""+exitEntry.getExitEntryCustomsResponse()+"\"。您可以提交异常处理申请向政府进行报告！";
            return back;
        }

        return "与链上信息完全一致，不存在数据异常！";

    }


}

