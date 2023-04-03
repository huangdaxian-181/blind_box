package com.example.demo.controller;

import com.example.demo.common.user.UserInfo;
import com.example.demo.domain.BbBoxDomain;
import com.example.demo.domain.BbPaperDomain;
import com.example.demo.service.BoxService;
import com.example.demo.service.PayService;
import com.example.demo.tools.Respond;
import com.example.demo.tools.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 盒子
 *
 * @author 搬砖的码农
 * @date 2023/03/18
 * @email
 **/
@Controller
@CrossOrigin(origins = "*")
public class BoxController {

    @Autowired
    BoxService boxService;

    @Autowired
    PayService payService;

    @GetMapping("/boxList")
    @ResponseBody
    public Respond boxList(@RequestParam int type) {

        if (type == 0) {
            List<BbBoxDomain> list = boxService.getList(UserInfo.userId, type);

            return Respond.success(200, "成功", list);
        } else if (type == 1) {
            List<BbPaperDomain> list = boxService.getOneList(UserInfo.userId);

            return Respond.success(200, "成功", list);
        }

        return Respond.success(200, "成功", "");
    }


    @PostMapping("/addBox")
    @ResponseBody
    public Respond addBox(@RequestBody BbBoxDomain bbBoxDomain) {
        SnowFlake snowFlake = new SnowFlake(0, 0);

        bbBoxDomain.setUser_id(UserInfo.userId);

        String order_no = String.valueOf(snowFlake.nextId());

        boolean bool = boxService.addBox(bbBoxDomain, order_no);

        if (bool) {
            return Respond.success(200, "成功", order_no);
        }

        return Respond.error(500, "错误");
    }

    @PostMapping("/delBox")
    @ResponseBody
    public Respond delBox(@RequestBody BbBoxDomain bbBoxDomain) {
        boolean bool = boxService.delBox(bbBoxDomain.getBox_id());

        if (bool) {
            return Respond.success(200, "删除成功");
        }
        return Respond.error(500, "删除错误");
    }
}
