package vip.zhangxiaoyi.back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhangyi on  16:18
 */
@Controller
@RequestMapping("/back")
public class SysuserController {
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/doLogin")
    public String doLogin(){
        //处理用户登录验证
        return "main";
    }
}
