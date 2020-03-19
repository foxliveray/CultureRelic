package com.edu.zju.culture.mbg.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.zju.culture.common.DataGridView;
import com.edu.zju.culture.common.ResultObj;
import com.edu.zju.culture.mbg.entity.Movement;
import com.edu.zju.culture.mbg.service.IMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        iMovementService.save(movement);
        return ResultObj.ADD_SUCCESS;
    }
}

