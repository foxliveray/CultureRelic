package com.edu.zju.culture.mbg.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.zju.culture.mbg.entity.*;
import com.edu.zju.culture.mbg.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("government")
public class GovernmentController {

    @Autowired
    IApplyService iApplyService;

    @Autowired
    IMovementService iMovementService;

    @Autowired
    ITradeService iTradeService;

    @Autowired
    IExitEntryService iExitEntryService;

    @Autowired
    IRelicService iRelicService;

    @RequestMapping("/getNum")
    public Map<String, Integer> getNum() {
        HashMap<String, Integer> res = new HashMap<>();

        QueryWrapper<Apply> applyQueryWrapper = new QueryWrapper<>();
        applyQueryWrapper.eq("check_status", 0);
        List<Apply> applyList = iApplyService.list(applyQueryWrapper);
        res.put("applyNum", applyList.size());

        QueryWrapper<Movement> movementQueryWrapper = new QueryWrapper<>();
        movementQueryWrapper.eq("check_status", 0);
        List<Movement> movementList = iMovementService.list(movementQueryWrapper);
        res.put("movementNum", movementList.size());

        QueryWrapper<Trade> tradeQueryWrapper = new QueryWrapper<>();
        tradeQueryWrapper.eq("check_status", 0);
        List<Trade> tradeList = iTradeService.list(tradeQueryWrapper);
        res.put("tradeNum", tradeList.size());

        QueryWrapper<ExitEntry> exitEntryQueryWrapper = new QueryWrapper<>();
        exitEntryQueryWrapper.eq("exit_entry_check_status", 0);
        List<ExitEntry> exitEntryList = iExitEntryService.list(exitEntryQueryWrapper);
        res.put("exitEntryNum", exitEntryList.size());

        QueryWrapper<Relic> relicQueryWrapper = new QueryWrapper<>();
        relicQueryWrapper.eq("relic_block_chain_status", 0);
        List<Relic> relicList = iRelicService.list(relicQueryWrapper);
        res.put("relicNum", relicList.size());
        return res;
    }
}
