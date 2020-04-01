package com.edu.zju.culture.mbg.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.zju.culture.common.ActiveUser;
import com.edu.zju.culture.common.ResultObj;
import com.edu.zju.culture.common.WebUtils;
import com.edu.zju.culture.mbg.entity.LogLogin;
import com.edu.zju.culture.mbg.entity.Notice;
import com.edu.zju.culture.mbg.entity.User;
import com.edu.zju.culture.mbg.service.ILogLoginService;
import com.edu.zju.culture.mbg.service.INoticeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;


/**
 * @author y4oung
 * @date 2020/3/14 12:42 AM
 * @description 前端登录请求控制器
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private ILogLoginService logLoginService;

    @ResponseBody
    @RequestMapping("/login")
    public ResultObj login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();

        AuthenticationToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
            WebUtils.getSession().setAttribute("user", activeUser.getUser());
            //记录登录日志 (之后可以把用户Role加进来）
            LogLogin logLogin = new LogLogin();
            logLogin.setLoginname(activeUser.getUser().getUsername());
            logLogin.setLoginip(WebUtils.getRequest().getRemoteAddr());
            logLogin.setLogintime(new Date());
            logLoginService.save(logLogin);

            return ResultObj.LOGIN_SUCCESS;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResultObj.LOGIN_ERROR_PASS;
        }
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
            Enumeration em = WebUtils.getSession().getAttributeNames();
            while(em.hasMoreElements()){
                WebUtils.getSession().removeAttribute(em.nextElement().toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "system/index/login";
    }

    @ResponseBody
    @RequestMapping("/getUserId")
    public String getUserId(HttpServletRequest servletRequest) {
        User user = (User) servletRequest.getSession().getAttribute("user");
        return String.valueOf(user.getId());
    }

}
