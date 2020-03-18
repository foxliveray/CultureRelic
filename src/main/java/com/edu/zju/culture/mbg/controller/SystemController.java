package com.edu.zju.culture.mbg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y4oung
 * @date 2020/3/14 12:13 AM
 * @description
 */
@Controller
@RequestMapping("sys")
public class SystemController {
    /**
     * 跳转到登录页面
     */
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "system/index/login";
    }

    /**
     * 跳转到首页
     */
    @RequestMapping("/index")
    public String index() {
        return "system/index/index";
    }

    /**
     * 跳转到首页导航栏右侧桌面
     */
    @RequestMapping("/console")
    public String console() {
        return "system/index/console1";
    }

    /**
     * 跳转到日志查询frame
     */
    @RequestMapping("/toLogLoginManager")
    public String toLogLoginManager() {
        return "system/loginInfo/loginLogManager.html";
    }

    /**
     * 跳转到菜单管理
     */
    @RequestMapping("/toMenuManager")
    public String toMenuManager() {
        return "system/menu/menuManager";
    }

    /**
     * 跳转到菜单管理-left
     */
    @RequestMapping("/toMenuLeft")
    public String toMenuLeft() {
        return "system/menu/menuLeft";
    }


    /**
     * 跳转到菜单管理--right
     */
    @RequestMapping("/toMenuRight")
    public String toMenuRight() {
        return "system/menu/menuRight";
    }

    /**
     * 跳转到权限管理
     */
    @RequestMapping("/toPermissionManager")
    public String toPermissionManager() {
        return "system/permission/permissionManager";
    }

    /**
     * 跳转到权限管理-left
     */
    @RequestMapping("/toPermissionLeft")
    public String toPermissionLeft() {
        return "system/permission/permissionLeft";
    }


    /**
     * 跳转到权限管理--right
     */
    @RequestMapping("/toPermissionRight")
    public String toPermissionRight() {
        return "system/permission/permissionRight";
    }

    /**
     * 跳转到鉴定页面
     */
    @RequestMapping("/toCheckRelic")
    public String toCheckRelic() {
        return "system/index/checkRelic";
    }

    @RequestMapping("/toRoleManager")
    public String toRoleManager() {
        return "system/role/roleManager";
    }


}
