package com.example.demo.controller;

import com.example.demo.common.user.UserInfo;
import com.example.demo.domain.BbNotesDomain;
import com.example.demo.domain.BbUserDomain;
import com.example.demo.service.UserService;
import com.example.demo.tools.Respond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 金币管理表
 *
 * @author 搬砖的码农
 * @date 2023/03/24
 * @email
 **/
@Controller
public class MoneyController {

    @Autowired
    private UserService userService;

    @PostMapping("/withdrawal")
    @ResponseBody
    public Respond withdrawal(@RequestBody BbUserDomain bbUserDomain) {
        boolean bool = userService.withdrawal(UserInfo.userId, bbUserDomain.getRewards());

        if (bool) {
            return Respond.success(200, "提现成功");
        }
        return Respond.error(500, "提现失败");
    }

    @GetMapping("/withdrawalRecord")
    @ResponseBody
    public Respond withdrawalRecord() {
        List<BbNotesDomain> list = userService.withdrawalList(UserInfo.userId);

        return Respond.success(200, "成功", list);
    }
}

