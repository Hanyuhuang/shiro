package com.hyh.shiro.controller;

import com.hyh.shiro.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/")
    public String toIndex(){
        return "index";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(User user, String rememberMe,ModelMap map){
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.isRemembered()){
            return "redirect:/index";
        }
        UsernamePasswordToken token = new UsernamePasswordToken(user.getPhone(),user.getPassword());
        if(rememberMe!=null){
           token.setRememberMe(true);
        }
        try {
            currentUser.login(token);
        } catch(IncorrectCredentialsException e){
            map.put("username",user.getUsername());
            map.put("msg", "密码错误");
            return "login";
        } catch (UnknownAccountException e) {
            map.put("msg", "用户不存在");
            return "login";
        } catch (DisabledAccountException e) {
            map.put("msg", "账号已被冻结");
            return "login";
        } catch (ConcurrentAccessException e) {
            map.put("msg", "账号不允许多处登录");
            return "login";
        } catch (AuthenticationException e){
            return "login";
        }
        return "redirect:/index";
    }

}
