package com.shiro.controller;

import com.shiro.ShiroTest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    SecurityManager securityManager;

    @RequestMapping(value = "/dispatcher", method={RequestMethod.GET})
    public String dispatcherJsp() {
        System.out.println("开始进入转发-->");

        return "testshiro";
    }

    @RequestMapping(value="testReturnw", method = {RequestMethod.GET})
    public ModelAndView testReturn() {
        ModelAndView modelAndView = new ModelAndView();
        ShiroTest shiroTest = new ShiroTest();
        Subject user = shiroTest.test();
        modelAndView.setViewName("testshiro");
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(value = "/testSecurity", method = { RequestMethod.GET })
    public ModelAndView testSecurity() {
        Subject user = SecurityUtils.getSubject();

        // 登陆
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "zhangsan");
        token.setRememberMe(true);

        try {
            user.login(token);
        } catch (UnknownAccountException uae) {
            System.out.println("用户名不存在:" + token.getPrincipal());
        } catch (IncorrectCredentialsException ice) {
            System.out.println("账户密码 " + token.getPrincipal()  + " 不正确!");
        } catch (LockedAccountException lae) {
            System.out.println("用户名 " + token.getPrincipal() + " 被锁定 !");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("testshiro");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

}
