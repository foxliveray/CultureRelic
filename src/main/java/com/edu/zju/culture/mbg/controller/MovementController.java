package com.edu.zju.culture.mbg.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.zju.culture.common.DataGridView;
import com.edu.zju.culture.common.ResultObj;
import com.edu.zju.culture.fabric.FabricHelper;
import com.edu.zju.culture.mbg.entity.Movement;
import com.edu.zju.culture.mbg.entity.Relic;
import com.edu.zju.culture.mbg.service.IMovementService;
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
@RequestMapping("/movement")
public class MovementController {
    @Autowired
    IMovementService iMovementService;

    @RequestMapping(value = "/list")
    public DataGridView selectAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int limit) {
        IPage<Movement> movement = new Page<>(page, limit);
        QueryWrapper<Movement> queryWrapper = new QueryWrapper<>();
        this.iMovementService.page(movement, queryWrapper);
        return new DataGridView(movement.getTotal(), movement.getRecords());
    }

    @RequestMapping(value = "/insertMovement")
    public ResultObj insertMovement(Movement movement, @RequestParam(value = "moveDateChange") String movementDateChange) {
        movementDateChange += " 00:00:00";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(movementDateChange, dtf);
        movement.setMoveDate(localDateTime);
        movement.setCheckStatus(0);
        movement.setMovementBlockChainStatus(0L);
        iMovementService.save(movement);
        return ResultObj.ADD_SUCCESS;
    }
    //流转上链
    @RequestMapping(value = "/addMovement")
    public void addMovementToblock(@RequestParam(value = "movementId")Long movementId,@RequestParam(value = "movementBlockChainStatus")Long movementBlockChainStatus,@RequestParam(value = "checkStatus")Integer checkStatus,@RequestParam(value = "movementResponse")String movementResponse,
                                @RequestParam("explanation")String explanation,@RequestParam("moveType")String moveType,@RequestParam("moveDate")String moveDate,@RequestParam("relicId")Long relicId,@RequestParam("fromId")Long fromId,@RequestParam("toId")Long toId) throws IOException {

        Movement movement=new Movement();
        movement.setMovementId(movementId);
        movement.setMovementBlockChainStatus(movementBlockChainStatus);
        movement.setCheckStatus(checkStatus);
        movement.setMovementResponse(movementResponse);
        iMovementService.updateById(movement);
        movement.setExplanation(explanation);
        movement.setMoveType(moveType);
        StringBuilder sb = new StringBuilder(moveDate);
        sb.setCharAt(10, ' ');
        String str = sb.toString();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime datetime = LocalDateTime.parse(str, fmt);
        movement.setMoveDate(datetime);
        movement.setRelicId(relicId);
        movement.setFromId(fromId);
        movement.setToId(toId);
        // 流转上链
        if(movement.getMovementBlockChainStatus()==1){
            FabricHelper fabricHelper=new FabricHelper();
            fabricHelper.init();
            fabricHelper.addMovement(movement);
        }


    }


    //与链上的流转信息比较
    @RequestMapping(value = "/compareMovement")
    public String compareMovementwithBlock(@RequestParam(value = "movementId")Long movementId,@RequestParam(value = "movementBlockChainStatus")Long movementBlockChainStatus,@RequestParam(value = "checkStatus")Integer checkStatus,@RequestParam(value = "movementResponse")String movementResponse,
                                   @RequestParam("explanation")String explanation,@RequestParam("moveType")String moveType,@RequestParam("moveDate")String moveDate,@RequestParam("relicId")Long relicId,@RequestParam("fromId")Long fromId,@RequestParam("toId")Long toId) throws IOException {

        Movement movement=new Movement();
        FabricHelper fabricHelper=new FabricHelper();
        fabricHelper.init();
        movement=fabricHelper.getMovement(movementId);
        if(!movement.getExplanation().equals(explanation)){
            String back="警告！流转说明信息出现异常，链上信息为：\""+movement.getExplanation()+"\"。您可以提交异常处理申请向政府进行报告！";
            return back;
        }
        if(!movement.getMoveType().equals(moveType)){
            String back="警告！流转类型信息出现异常，链上信息为：\""+movement.getMoveType()+"\"。您可以提交异常处理申请向政府进行报告！";
            return back;
        }

        if(!moveDate.equals(movement.getMoveDate().toString())){
            String back="警告！流转发生时间信息出现异常，链上信息为：\""+movement.getMoveDate()+"\"。您可以提交异常处理申请向政府进行报告！";
            return back;
        }


        if(movement.getFromId()!=fromId){
            String back="警告！流转发起人信息出现异常，链上信息为：\""+movement.getFromId()+"\"。您可以提交异常处理申请向政府进行报告！";
            return back;
        }
        if(movement.getToId()!=toId){
            String back="警告！流转接受人信息出现异常，链上信息为：\""+movement.getToId()+"\"。您可以提交异常处理申请向政府进行报告！";
            return back;
        }
        if(!movement.getMovementResponse().equals(movementResponse)){
            String back="警告！流转审核信息出现异常，链上信息为：\""+movement.getMovementResponse()+"\"。您可以提交异常处理申请向政府进行报告！";
            return back;
        }

        return "与链上信息完全一致，不存在数据异常！";

    }
}

