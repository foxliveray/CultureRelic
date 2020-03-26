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

    /**
     *跳转到政府文物上链审核页面
     */
    @RequestMapping("/toGovCheckRelic")
    public String toGovCheckRelic() {
        return "system/index/gov_checkRelic";
    }

    @RequestMapping("/toRoleManager")
    public String toRoleManager() {
        return "system/role/roleManager";
    }
    /**
     * 跳转到博物馆查看流转页面
     */
    @RequestMapping("/toCheckMovement")
    public String toCheckApply() {
        return "system/index/checkMovement";
    }

    /**
     *跳转到政府文物流转审核页面
     *
     */
    @RequestMapping("/toGovCheckMovement")
    public String toGovCheckMovement() {
        return "system/index/gov_checkMovement";
    }

    /**
     *  跳转到交易所查看交易信息
     *
     */
    @RequestMapping("/toCheckTrade")
    public String toCheckOrder() {
        return "system/index/checkTrade";
    }

    /**
     * 跳转到海关查看出入境信息
     */
    @RequestMapping("/toCheckExitEntry")
    public String toCheckExitEntry() {
        return "system/index/checkExitEntry";
    }

    /**
     *  跳转到政府审核交易信息页面
     *
     */
    @RequestMapping("/toGovCheckTrade")
    public String toGovCheckOrder() {
        return "system/index/gov_checkTrade";
    }

    /**
     *  跳转到政府审核出入境申请信息页面
     *
     */
    @RequestMapping("/toGovCheckExitEntry")
    public String toGovCheckExitEntry() {
        return "system/index/gov_checkExitEntry";
    }

    /**
     *  跳转到政府审核异常流转申诉处理页面
     *
     */
    @RequestMapping("/toGovCheckApply")
    public String toGovCheckApply() {
        return "system/index/gov_checkApply";
    }

    /**
     *  跳转到政府审核异常流转申诉处理详情页面
     *  （注：该页面暂时还没创建）
     *
     */
    @RequestMapping("/toGovCheckApplyDetail")
    public String toGovCheckApplyDetail() {
        return "system/index/gov_checkApplyDetail";
    }

    /**
     * 普通用户跳转到查看文物
     */
    @RequestMapping("/toSeeRelic")
    public String toSeeRelic() {
        return "system/index/user_seeRelic";
    }

    /**
     * 普通用户跳转到文物溯源界面
     */
    @RequestMapping("/toSeeMovement")
    public String toSeeMovement() {
        return "system/index/user_seeMovement";
    }


    /**
     * 跳转到用户管理
     */
    @RequestMapping("/toUserManager")
    public String toUserManager(){
        return "system/user/UserManager";
    }

    /**
        * 跳转到用户个人设置
     */
    @RequestMapping("/toSeeInfo")
    public String toSeeUserInfo(){
        return "system/index/user_seeInfo";
    }
}
