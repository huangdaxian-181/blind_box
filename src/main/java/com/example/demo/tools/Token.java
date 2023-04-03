package com.example.demo.tools;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * token生成
 *
 * @author 搬砖的码农
 * @date 2023/03/20
 * @email
 **/
public class Token {
    /**
     * 设置过期时间
     */
    private static final long EXPIRE_DATE = 1000 * 10 * 60 * 24;


    /**
     * token密钥
     */
    private static final String TOKEN_SECRET = "huang28319031HHda";

    /**
     * 生成token
     *
     * @param id
     */
    public static String createToken(int id) {

        String token = "";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");

            //携带username，password信息，生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("id", id)
                    .withClaim("time", new Date()).withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return token;
    }


    /**
     * 解析token
     *
     * @param token token
     * @return 结果
     */
    public static boolean verify(String token){
        /**
         * @desc   验证token，通过返回true
         * @create 2019/1/18/018 9:39
         * @params [token]需要校验的串
         **/
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }

    /**
     * 获取token里面的用户id
     * @param token
     * */
    public static int getUserId(String token){
        DecodedJWT s = JWT.decode(token);

        return s.getClaim("id").asInt();
    }
}
