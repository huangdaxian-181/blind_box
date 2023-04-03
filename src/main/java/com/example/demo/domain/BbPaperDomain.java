package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 领取纸条表
 *
 * @author 搬砖的码农
 * @date 2023/03/19
 * @email
 **/
@Setter
@Getter
public class BbPaperDomain {
    private int paper_id;

    /**
     * 盒子id
     */
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
     * 获取图片
     */
    private List<BbImageDomain> image;

    private Date create_time;

}
