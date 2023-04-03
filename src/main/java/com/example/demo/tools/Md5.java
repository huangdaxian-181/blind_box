package com.example.demo.tools;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author 搬砖的码农
 * @date 2023/03/06
 * @email
 **/
public class Md5 {

    /**
     * sign 签名 （参数名按ASCII码从小到大排序（字典序）+key+MD5+转大写签名）
     *
     * @return
     */
    public static String createSign(Map params, String key) {

        Map<String, Object> param = new TreeMap<>(params);

        StringBuffer sbkey = new StringBuffer();
        // entrySet 所有参与传参的参数按照accsii排序（升序）
        Set es = param.entrySet();
        Iterator it = es.iterator();

        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();

            //空值不传递，不参与签名组串
            if (null != v && !"".equals(v)) {
                sbkey.append(k + "=" + v + "&");
            }
        }

        String str = sbkey.substring(0,sbkey.length()-1)+key;

        //MD5加密,结果转换为大写字符
        String sign = encodeByMD5(str);

        System.out.println(sign);

        return sign;
    }

    /**
     * MD5加密之方法二
     *
     * @return 16进制加密字符串
     * @explain java实现
     */
    public static String encodeByMD5(String str) {
        try {

            // 生成一个MD5加密计算摘要

            MessageDigest md = MessageDigest.getInstance("MD5");

            // 计算md5函数

            md.update(str.getBytes());

            return byteArrayToHexString(md.digest());

        } catch (Exception e) {

            throw new RuntimeException("MD5加密出现错误");

        }
    }


    private static String getHmacSha256(String message, String key) {

        String outPut = null;

        try {

            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");

            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");

            sha256_HMAC.init(secret_key);

            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());

            outPut = byteArrayToHexString(bytes);

        } catch (Exception e) {

            throw new RuntimeException("HmacSHA256加密出现错误");

        }

        return outPut;

    }

    private static String byteArrayToHexString(byte[] b) {

        StringBuilder sb = new StringBuilder();

        String stmp;

        for (int n = 0; b != null && n < b.length; n++) {

            stmp = Integer.toHexString(b[n] & 0XFF);

            if (stmp.length() == 1)

                sb.append('0');

            sb.append(stmp);

        }

        return sb.toString().toLowerCase();

    }
}
