package com.example.demo.mapper;

import com.example.demo.domain.BbPaperDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 纸条
 * @author huangdaxian
 * @date 2023/03/19
 * @email 3081476398@qq.com
 **/
@Mapper
public interface BbPaperMapper {
    /**
     * 查询我收取到的纸条表
     * @param userId
     * */
    List<BbPaperDomain> findOne(int userId);
}
