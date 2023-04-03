package com.example.demo.tools;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * 封装响应类
 *
 * @author 搬砖的码农
 * @date 2023/03/18
 * @email
 **/
public class Respond extends JSONObject{
    /**状态码*/
    private String CODE = "code";

    /**错误信息*/
    private String MSG = "msg";

    /**返回内容*/
    private String DATA = "data";

    /**时间*/
    private String TIME = "time";

    /**
     * 构造函数
     * */
    public Respond(){
    }

    public Respond(int code,String msg,Object data){
        super.put(CODE,code);
        super.put(MSG,msg);

        if(!data.equals("") || data == null){
            super.put(DATA,data);
        }

        super.put(TIME,new Date());
    }

    public Respond(int code,String msg){
        super.put(CODE,code);
        super.put(MSG,msg);

        super.put(TIME,new Date());
    }

    /**
     * 成功
     * */
    public static final Respond success(int code,String msg,Object data){return new Respond(code,msg,data);}

    public static final Respond success(int code,String msg){return new Respond(code,msg);}

    /**
     * 失败
     * */
    public static final Respond error(int code,String msg){return new Respond(code,msg);}
}
