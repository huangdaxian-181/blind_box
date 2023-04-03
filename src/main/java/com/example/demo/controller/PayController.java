package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 发起支付
 *
 * @author 搬砖的码农
 * @date 2023/03/06
 * @email
 **/
@Controller
public class PayController {

    @Autowired
    private PayService payService;

    @GetMapping("/payOrder")
    @ResponseBody
    public String payOrder(@RequestParam String out_trade_no){
        return payService.payOrders(out_trade_no);
    }



}
