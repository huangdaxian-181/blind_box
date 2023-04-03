package com.example.demo.service;

import com.example.demo.domain.BbBoxDomain;
import com.example.demo.domain.BbPaperDomain;

import java.util.List;

/**
 * 盒子
 *
 * @author huangdaxian
 * @date 2023/03/18
 * @email 3081476398@qq.com
 **/
public interface BoxService {
    /**
     * 盒子
     *
     * @param type   0我放入 1我收到
     * @param userId
     */
    List<BbBoxDomain> getList(int userId, int type);

    /**
     * 我抽到的
     *
     * @param userId
     */
    List<BbPaperDomain> getOneList(int userId);

    /**
     * 添加纸条
     *
     * @param bbBoxDomain
     */
    boolean addBox(BbBoxDomain bbBoxDomain, String order_no);

    /**
     * 删除纸条
     *
     * @param box_id
     */
    boolean delBox(int box_id);
}
