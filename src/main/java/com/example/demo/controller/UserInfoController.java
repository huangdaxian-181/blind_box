package com.example.demo.controller;

import com.example.demo.common.user.UserInfo;
import com.example.demo.domain.BbUserDomain;
import com.example.demo.service.UserService;
import com.example.demo.tools.Respond;
import org.apache.catalina.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户详细信息
 *
 * @author 搬砖的码农
 * @date 2023/03/21
 * @email
 **/
@Controller
public class UserInfoController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    @ResponseBody
    public Respond userInfo() {

        BbUserDomain info = userService.userInfo(UserInfo.userId);

        if(info == null){
            return Respond.error(500,"发生错误");
        }

        return Respond.success(200, "成功", info);
    }
}
