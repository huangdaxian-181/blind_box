package com.example.demo.mapper;

import com.example.demo.domain.BbImageDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 图片
 *
 * @author huangdaxian
 * @date 2023/03/19
 * @email 3081476398@qq.com
 **/
@Mapper
public interface BbImageMapper {
    /**
     * 查询box_id下面的图片
     *
     * @param box_id
     */
    List<BbImageDomain> findImgAll(int box_id);

    /**
     * 添加图片
     *
     * @param bbImageDomain
     */
    boolean addImg(BbImageDomain bbImageDomain);
}
