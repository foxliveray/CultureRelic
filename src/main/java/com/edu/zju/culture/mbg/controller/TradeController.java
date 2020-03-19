package com.edu.zju.culture.mbg.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.zju.culture.common.DataGridView;
import com.edu.zju.culture.common.ResultObj;
import com.edu.zju.culture.mbg.entity.Trade;
import com.edu.zju.culture.mbg.service.ITradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author y4oung
 * @since 2020-03-19
 */
@RestController
@RequestMapping("/trade")
public class TradeController {
    @Autowired
    ITradeService iTradeService;

    @RequestMapping("/list")
    public DataGridView selectAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int limit) {
        IPage<Trade> order = new Page<>(page, limit);
        QueryWrapper<Trade> queryWrapper = new QueryWrapper<>();
        this.iTradeService.page(order, queryWrapper);
        return new DataGridView(order.getTotal(), order.getRecords());
    }

    @RequestMapping(value = "/insertTrade")
    public ResultObj insertMovement(Trade trade, @RequestParam(value = "orderDateChange") String orderDateChange) {
        orderDateChange += " 00:00:00";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(orderDateChange, dtf);
        trade.setOrderDate(localDateTime);
        trade.setCheckStatus("0");
        trade.setOrderBlockChainStatus(0);
        iTradeService.save(trade);
        return ResultObj.ADD_SUCCESS;
    }
}

