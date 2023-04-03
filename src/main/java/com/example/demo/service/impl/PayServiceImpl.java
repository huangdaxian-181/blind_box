package com.example.demo.service.impl;

import com.example.demo.service.PayService;
import com.example.demo.tools.Md5;
import com.example.demo.tools.PostRequest;
import com.example.demo.tools.SnowFlake;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * @author 搬砖的码农
 * @date 2023/03/06
 * @email
 **/
@Service
@Configuration
public class PayServiceImpl implements PayService {

    @Value("${pay.url}")
    private String URL;

    @Value("${pay.pid}")
    private int PID;

    @Value("${pay.key}")
    private String key;

    @Value("${pay.sitename}")
    private String sitename;

    @Override
    public String payOrders(String out_trade_no) {

        //生成用户唯一订单号
        SnowFlake snowFlake = new SnowFlake(0, 0);

        Map<String, Object> sortedMap = new HashMap<>();

        //商户ID
        sortedMap.put("pid", PID);

        //支付方式
        sortedMap.put("type", "qqpay");

        //商户订单号
        sortedMap.put("out_trade_no", out_trade_no);

        //异步通知地址
        String notify_url = "http://fubf3t.natappfree.cc/notifyOrderQueue";
        sortedMap.put("notify_url", notify_url);

        //跳转通知地址
        String return_url = "http://fubf3t.natappfree.cc/returnUrl";
        sortedMap.put("return_url", return_url);

        //商品名称
        sortedMap.put("name", "抽取盲盒");

        //商品金额
        sortedMap.put("money", "1");

        //将发送或接收到的所有参数按照参数名ASCII码从小到大排序（a-z），sign、sign_type
        //加密成md5加密
        String sign = Md5.createSign(sortedMap, key);

        sortedMap.put("sign", sign);

        //签名类型
        sortedMap.put("sign_type", "MD5");

        String html = PostRequest.doPost(URL, sortedMap);

        return html;

    }
}
