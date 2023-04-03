package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户
 *
 * @author 搬砖的码农
 * @date 2023/03/20
 * @email
 **/
@Setter
@Getter
public class BbUserDomain {
    private int userId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 奖励金
     */
    private float rewards;

    /**
     * 提现金额
     */
    private int total;

    /**
     * 接收验证码
     */
    private int code;

    /**
     * 密码
     */
    private String pwd;


    /**
     * 头像
     */
    private String avatar;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 下下级红娘
     */
    private int InferiorMatch;

    /**
     * 下级红娘数
     */
    private int downMatch;

    /**
     * 推广用户数
     */
    private int userNum;

    /**
     * 0普通用户 1审核员
     */
    private int type;


    private Date create_time;
}
