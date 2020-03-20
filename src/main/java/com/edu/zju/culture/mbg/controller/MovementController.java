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
    public void addMovementToblock(@RequestParam(value = "movementId")Long movementId,@RequestParam(value = "relicBlockChainStatus")Long relicBlockChainStatus,@RequestParam(value = "checkStatus")Integer checkStatus,@RequestParam(value = "movementResponse")String movementResponse,
                                @RequestParam("explanation")String explanation,@RequestParam("moveType")String moveType,@RequestParam("moveDate")String moveDate,@RequestParam("relicId")Long relicId,@RequestParam("fromId")Long fromId,@RequestParam("toId")Long toId) throws IOException {

        Movement movement=new Movement();
        movement.setMovementId(movementId);
        movement.setMovementBlockChainStatus(relicBlockChainStatus);
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
}

