package com.edu.zju.culture.mbg.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.zju.culture.common.*;
import com.edu.zju.culture.mbg.entity.Permission;
import com.edu.zju.culture.mbg.entity.User;
import com.edu.zju.culture.mbg.service.IPermissionService;
import com.edu.zju.culture.mbg.service.IRoleService;
import com.edu.zju.culture.vo.PermissionVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author y4oung
 * @date 2020/3/15 12:24 PM
 * @description
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IRoleService roleService;

    /**
     * 加载菜单json数据
     *
     * @param permissionVo
     * @return
     */
    @RequestMapping("/loadIndexLeftMenuJson")
    public List<TreeNode> loadIndexLeftMenuJson(PermissionVo permissionVo) {
        //查询所有菜单
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", Constant.TYPE_MNEU);
        queryWrapper.eq("available", Constant.AVAILABLE_TRUE);
        //设置只能查到菜单
        User user = (User) WebUtils.getSession().getAttribute("user");
        List<Permission> list = null;
        if (user.getType() == Constant.USER_TYPE_SUPER) {
            list = permissionService.list(queryWrapper);
        } else {
            //根据用户ID+角色+权限去查询
            Long userId = user.getId();
            //根据用户ID查询其包含的角色
            List<Integer> currentUserRoleIds = roleService.queryUserRoleIdsByUid(Math.toIntExact(userId));
            //根据角色ID获取菜单和权限ID(有重复所以用set）
            Set<Integer> pids = new HashSet<>();
            for(Integer rid:currentUserRoleIds){
                List<Integer> permissionIds = roleService.queryRolePermissionIdsByRid(rid);
                pids.addAll(permissionIds);
            }

            //根据角色ID查询权限
            if(pids.size()>0){
                queryWrapper.in("id",pids);
                list = permissionService.list(queryWrapper);
            }else{
                list = new ArrayList<>();
            }
        }

        List<TreeNode> treeNodes = new ArrayList<>();
        for (Permission p : list) {
            Integer id = p.getId();
            Integer pid = p.getPid();
            String title = p.getTitle();
            String icon = p.getIcon();
            String href = p.getHref();
            Integer type = p.getType().equals("menu") ? 0 : 1;
            Boolean spread = p.getOpen() == Constant.OPEN_TRUE ? true : false;
            treeNodes.add(new TreeNode(id, pid, title, icon, href, spread, type));
        }
        //构造层级关系
        List<TreeNode> list2 = TreeNodeBuilder.build(treeNodes, 1);
        return list2;
    }

    /****************菜单管理开始****************/

    /**
     * 加载菜单管理左边的菜单树的json
     */
    @RequestMapping("/loadMenuManagerLeftTreeJson")
    public DataGridView loadMenuManagerLeftTreeJson(PermissionVo permissionVo) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", Constant.TYPE_MNEU);
        List<Permission> list = this.permissionService.list(queryWrapper);
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Permission menu : list) {
            Boolean spread = menu.getOpen() == 1 ? true : false;
            treeNodes.add(new TreeNode(menu.getId(), menu.getPid(), menu.getTitle(), spread));
        }
        return new DataGridView(treeNodes);
    }

    /**
     * 查询
     */
    @RequestMapping("/loadAllMenu")
    public DataGridView loadAllMenu(PermissionVo permissionVo) {
        IPage<Permission> page = new Page<>(permissionVo.getPage(), permissionVo.getLimit());
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(permissionVo.getId() != null, "id", permissionVo.getId()).or().eq(permissionVo.getId() != null, "pid", permissionVo.getId());
        queryWrapper.eq("type", Constant.TYPE_MNEU);
        queryWrapper.like(StringUtils.isNotBlank(permissionVo.getTitle()), "title", permissionVo.getTitle());
        queryWrapper.orderByAsc("ordernum");
        this.permissionService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());

    }

    /**
     * 加载最大的排序码
     *
     * @param
     * @return
     */
    @RequestMapping("/loadMenuMaxOrderNum")
    public Map<String, Object> loadMenuMaxOrderNum() {
        Map<String, Object> map = new HashMap<String, Object>();

        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        //倒序查第一条
        queryWrapper.orderByDesc("ordernum");
        IPage<Permission> page = new Page<>(1, 1);
        List<Permission> list = this.permissionService.page(page, queryWrapper).getRecords();
        if (list.size() > 0) {
            map.put("value", list.get(0).getOrdernum() + 1);
        } else {
            map.put("value", 1);
        }
        return map;
    }

    /**
     * 添加
     *
     * @param permissionVo
     * @return
     */
    @RequestMapping("/addMenu")
    public ResultObj addMenu(PermissionVo permissionVo) {
        try {
            permissionVo.setType(Constant.TYPE_MNEU);//设置添加类型
            this.permissionService.save(permissionVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改
     *
     * @param permissionVo
     * @return
     */
    @RequestMapping("/updateMenu")
    public ResultObj updateMenu(PermissionVo permissionVo) {
        try {
            this.permissionService.updateById(permissionVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 查询当前的ID的菜单有没有子菜单
     */
    @RequestMapping("checkMenuHasChildrenNode")
    public Map<String, Object> checkMenuHasChildrenNode(PermissionVo permissionVo) {
        Map<String, Object> map = new HashMap<String, Object>();

        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", permissionVo.getId());
        List<Permission> list = this.permissionService.list(queryWrapper);
        if (list.size() > 0) {
            map.put("value", true);
        } else {
            map.put("value", false);
        }
        return map;
    }

    /**
     * 删除
     *
     * @param permissionVo
     * @return
     */
    @RequestMapping("deleteMenu")
    public ResultObj deleteMenu(PermissionVo permissionVo) {
        try {
            this.permissionService.removeById(permissionVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /****************菜单管理结束****************/


}
