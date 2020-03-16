package com.edu.zju.culture.mbg.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.zju.culture.common.DataGridView;
import com.edu.zju.culture.mbg.entity.Role;
import com.edu.zju.culture.mbg.service.IPermissionService;
import com.edu.zju.culture.mbg.service.IRoleService;
import com.edu.zju.culture.vo.RoleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色管理 前端控制器
 * </p>
 *
 * @author y4oung
 * @since 2020-03-16
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    /**
     * 查询
     */
//    @RequestMapping("/loadAllRole")
//    public DataGridView loadAllRole(RoleVo roleVo){
//        IPage<Role> page=new Page<>(roleVo.getPage(), roleVo.getLimit());
//        QueryWrapper<Role> queryWrapper=new QueryWrapper<>();
//        queryWrapper.like(StringUtils.isNotBlank(roleVo.getName()))
//    }
}

