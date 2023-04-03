package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 支付成功跳转通知
 *
 * @author 搬砖的码农
 * @date 2023/03/06
 * @email
 **/
@Controller
public class ReturnController {
    @GetMapping("/returnUrl")
    public String returnUrl(){
        return "您支付成功了.........";
    }
}
