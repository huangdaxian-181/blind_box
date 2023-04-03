package com.example.demo.controller;

import com.example.demo.domain.BbUserDomain;
import com.example.demo.mapper.BbUserMapper;
import com.example.demo.service.UserService;
import com.example.demo.tools.Respond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 注册登录控制器
 *
 * @author 搬砖的码农
 * @date 2023/03/20
 * @email
 **/
@Controller
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private BbUserMapper bbUserMapper;

    @PostMapping("/register")
    @ResponseBody
    public Respond register(@RequestBody BbUserDomain bbUserDomain) {
        //判断验证码是否一样
        if (bbUserDomain.getCode() != 1000) {
            return Respond.error(1005,"验证码错误");
        }

        //添加用户
        int code = userService.register(bbUserDomain);

        if (code == 1006) {

            return Respond.error(1006, "用户已存在");
        }
        return Respond.success(200, "注册成功");
    }


    @PostMapping("/login")
    @ResponseBody
    public Respond login(@RequestBody BbUserDomain bbUserDomain){
        //判断手机号是否存在
        BbUserDomain user = bbUserMapper.findOneUser(bbUserDomain.getPhone());

        if (user == null) {
            return Respond.error(500,"手机号不存在");
        }


        String str = userService.login(bbUserDomain);

        if(str!=""){
           return Respond.success(200,"登录成功",str);
        }

        return Respond.error(500,"手机号或密码错误");



    }
}
