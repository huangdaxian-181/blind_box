package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 图片
 *
 * @author 搬砖的码农
 * @date 2023/03/19
 * @email
 **/
@Setter
@Getter
public class BbImageDomain {
    private int image_id;

    /**
     * 地址
     */
    private String url;

    /**
     * 盒子id
     */
    private int box_id;


    private Date create_time;
}
