package com.example.demo.service;

/**
 * 支付
 *
 * @author huangdaxian
 * @date 2023/03/06
 * @email 3081476398@qq.com
 **/
public interface PayService {
    /**
     * 调用发起支付
     * @param out_trade_no
     * */
    String payOrders(String out_trade_no);
}
