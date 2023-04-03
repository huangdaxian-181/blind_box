package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 盒子表
 *
 * @author 搬砖的码农
 * @date 2023/03/17
 * @email
 **/
@Setter
@Getter
public class BbBoxDomain {

    private int box_id;

    /**
     * 用户id
     */
    private int user_id;

    /**
     * 简介
     */
    private String content;

    /**
     * 0女生 1男生
     */
    private int sex;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 1正在审核中 0拒绝
     */
    private int type;

    /**
     * 拒绝原因
     */
    private String remark;

    /**
     * 联系方式类型 0手机号 1微信 2qq
     */
    private int contact_type;

    /**
     * 获取图片
     */
    private List<BbImageDomain> image;

    /**
     * 获取图片
     * */
    private String[] img;

    /**
     * 地址
     */
    private int address;

    /**
     *0抽到一次就销毁 1一直保留
     * */
    private int life;

    /**
     * 支付状态
     */
    private int pay_type;

    /**
     * 订单编号
     */
    private String order_no;

    private int status;

    private int is_delete;

    private Date create_time;

    private Date update_time;
}
