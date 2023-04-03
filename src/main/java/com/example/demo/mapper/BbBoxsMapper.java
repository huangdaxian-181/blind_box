package com.example.demo.mapper;

import com.example.demo.domain.BbBoxDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 盒子数据类
 *
 * @author huangdaxian
 * @date 2023/03/17
 * @email 3081476398@qq.com
 **/
@Mapper
public interface BbBoxsMapper {
    /**
     * 查询单个用户全部数据
     * 1对多图片
     *
     * @param id
     */
    List<BbBoxDomain> findUserAll(int id, int type);


    /**
     * 添加数据
     * 添加纸条
     *
     * @param bbBoxDomain
     */
    boolean addBox(BbBoxDomain bbBoxDomain);

    /**
     * 删除
     *
     * @param box_id
     */
    boolean delBox(int box_id);
}
