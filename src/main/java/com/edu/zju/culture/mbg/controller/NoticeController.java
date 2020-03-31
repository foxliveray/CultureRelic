package com.edu.zju.culture.mbg.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.zju.culture.common.DataGridView;
import com.edu.zju.culture.common.ResultObj;
import com.edu.zju.culture.common.WebUtils;
import com.edu.zju.culture.mbg.entity.Notice;
import com.edu.zju.culture.mbg.entity.User;
import com.edu.zju.culture.mbg.service.INoticeService;
import com.edu.zju.culture.vo.NoticeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author y4oung
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private INoticeService noticeService;

    /**
     * 查询
     */
    @RequestMapping("/loadAllNotice")
    public DataGridView loadAllNotice(NoticeVo noticeVo){
        IPage<Notice> page = new Page<>(noticeVo.getPage(),noticeVo.getLimit());
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(noticeVo.getTitle()),"title",noticeVo.getTitle());
        queryWrapper.like(StringUtils.isNotBlank(noticeVo.getOpername()),"opername",noticeVo.getOpername());
        queryWrapper.ge(noticeVo.getStartTime()!=null,"createTime",noticeVo.getStartTime());
        queryWrapper.le(noticeVo.getEndTime()!=null,"createTime",noticeVo.getEndTime());
        queryWrapper.orderByDesc("createTime");
        this.noticeService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加公告
     */
    @RequestMapping("/addNotice")
    public ResultObj addNotice(NoticeVo noticeVo){
        try {
            noticeVo.setCreatetime(new Date());
            User user = (User) WebUtils.getSession().getAttribute("user");
            noticeVo.setOpername(user.getUsername());
            this.noticeService.save(noticeVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改公告
     */
    @RequestMapping("/updateNotice")
    public ResultObj updateNotice(NoticeVo noticeVo){
        try{
            this.noticeService.updateById(noticeVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除公告
     */
    @RequestMapping("/deleteNotice")
    public ResultObj deleteNotice(Integer id){
        try {
            this.noticeService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 获取最新公告
     */
    @RequestMapping("/getLastNotice")
    public DataGridView getLastNotice(){
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("createTime");
        List<Notice> notice = this.noticeService.list(queryWrapper);
        return new DataGridView((long) 1,notice.get(0));
    }
}

