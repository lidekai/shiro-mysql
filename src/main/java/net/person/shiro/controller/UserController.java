package net.person.shiro.controller;

import net.person.shiro.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2018/6/20.
 */
@Controller
public class UserController {
    @RequestMapping(value="/subLogin",
                    method = RequestMethod.POST,
                    produces = "application/json;charset=utf-8")
    @ResponseBody
    public String subLogin(User user) throws Exception{
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(
                                                                user.getUsername(),
                                                                user.getPassword()
                                                             );
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return e.getMessage();
        }
    if(subject.hasRole("admin")){
            return "有admin权限";
        }
        return "无admin权限";
    }
}
