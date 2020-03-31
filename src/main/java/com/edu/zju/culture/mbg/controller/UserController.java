package com.edu.zju.culture.mbg.controller;


import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.zju.culture.common.*;
import com.edu.zju.culture.mbg.entity.Role;
import com.edu.zju.culture.mbg.entity.User;
import com.edu.zju.culture.mbg.service.IRoleService;
import com.edu.zju.culture.mbg.service.IUserService;
import com.edu.zju.culture.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author y4oung
 * @since 2020-03-13
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    /**
     * 用户全查询
     */
    @RequestMapping("/loadAllUser")
    public DataGridView loadAllUser(UserVo userVo) {
        IPage<User> page = new Page<>(userVo.getPage(), userVo.getLimit());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(userVo.getUsername()), "username", userVo.getUsername());
        queryWrapper.eq(userVo.getStatus() != null, "status", userVo.getStatus());
        queryWrapper.eq("type", Constant.USER_TYPE_NORMAL); //查询系统用户
        this.userService.page(page, queryWrapper);
        System.out.println(userService.getClass().getSimpleName());
        List<User> list = page.getRecords();
        for (User user : list) {
            Long uid = user.getId();
            String role = this.userService.getRoleByUid(uid);
            user.setRole(role);
        }
        List<User> res = new ArrayList<>();
        if (StringUtils.isNotBlank(userVo.getRole())) {
            for (User user : list) {
                if (user.getRole() != null && user.getRole().indexOf(userVo.getRole()) != -1) {
                    res.add(user);
                }
            }
        } else {
            res = list;
        }
        return new DataGridView((long) res.size(), res);
    }

    /**
     * 添加用户
     */
    @RequestMapping("/addUser")
    public ResultObj addUser(UserVo userVo) {
        try {
            userVo.setType(Constant.USER_TYPE_NORMAL);//设置类型
            userVo.setCreateTime(new Date());
            String salt = IdUtil.simpleUUID().toUpperCase();
            userVo.setSalt(salt);//设置盐
            userVo.setPassword(new Md5Hash(Constant.USER_DEFAULT_PWD, salt, 2).toString());//设置密码
            userVo.setIcon("/res/admin/images/avatar.jpg");//设置默认头像
            this.userService.save(userVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改用户
     */
    @RequestMapping("/updateUser")
    public ResultObj updateUser(UserVo userVo) {
        try {
            if(!(userVo.getIcon()!=null&&userVo.getIcon().equals(Constant.IMAGES_DEFAULTAVATAR_JPG))){
                if(userVo.getIcon().endsWith("_temp")){
                    String newName = AppFileUtils.renameFile(userVo.getIcon());
                    userVo.setIcon(newName);
                    //删除原先的图片
                    Integer id = Math.toIntExact(userVo.getId());
                    String oldPath = this.userService.getById(id).getIcon();
                    if(!oldPath.equals(Constant.IMAGES_DEFAULTAVATAR_JPG)){
                        AppFileUtils.removeFileByPath(oldPath);
                    }
                }
            }
            this.userService.updateById(userVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除用户
     */
    @RequestMapping("/deleteUser")
    public ResultObj deleteUser(Integer id) {
        try {
            this.userService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 重置用户密码
     */
    @RequestMapping("/resetPwd")
    public ResultObj resetPwd(Long id) {
        try {
            User user = new User();
            user.setId(id);
            String salt = IdUtil.simpleUUID().toUpperCase();
            user.setSalt(salt);//设置盐
            user.setPassword(new Md5Hash(Constant.USER_DEFAULT_PWD, salt, 2).toString());//设置密码
            this.userService.updateById(user);
            return ResultObj.RESET_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.RESET_ERROR;
        }
    }

    /**
     * 修改用户密码
     */
    @RequestMapping("/modifyPwd")
    public ResultObj modifyPwd(Long id,String password) {
        try {
            User user = new User();
            user.setId(id);
            String salt = IdUtil.simpleUUID().toUpperCase();
            user.setSalt(salt);//设置盐
            user.setPassword(new Md5Hash(password, salt, 2).toString());//设置密码
            this.userService.updateById(user);
            return ResultObj.RESET_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.RESET_ERROR;
        }
    }

    /**
     * 根据用户ID查询角色并选中已拥有的角色
     */
    @RequestMapping("/initRoleByUserId")
    public DataGridView initRoleByUserId(Integer id) {
        //1,查询所有可用的角色
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("available", Constant.AVAILABLE_TRUE);
        List<Map<String, Object>> listMaps = this.roleService.listMaps(queryWrapper);

        //2,查询当前用户拥有的角色ID集合
        List<Integer> currentUserRoleIds = this.roleService.queryUserRoleIdsByUid(id);
        for (Map<String, Object> map : listMaps) {
            Boolean LAY_CHECKED = false;
            Long roleId = (Long) map.get("id");
            for (Integer rid : currentUserRoleIds) {
                if (Long.valueOf(rid) == roleId) {
                    LAY_CHECKED = true;
                    break;
                }
            }
            map.put("LAY_CHECKED", LAY_CHECKED);
        }
        return new DataGridView(Long.valueOf(listMaps.size()), listMaps);
    }

    /**
     * 保存用户和角色的关系
     */
    @RequestMapping("/saveUserRole")
    public ResultObj saveUserRole(Integer uid, Integer[] ids) {
        try {
            this.userService.saveUserRole(uid, ids);
            return ResultObj.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }

    }

    /**
     * 获取用户信息
     */
    @RequestMapping("/getUserInfo")
    public DataGridView getUserInfo(Integer id) {
        User user = this.userService.getById(id);
        return new DataGridView(user);
    }
}

