package com.example.demo.mapper;

import com.example.demo.domain.BbNotesDomain;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 提现记录表
 *
 * @author huangdaxian
 * @date 2023/03/25
 * @email 3081476398@qq.com
 **/
@Mapper
public interface BbNotesMapper {
    /**
     * 添加记录
     *
     * @param bbNotesDomain
     */
    boolean addNotes(BbNotesDomain bbNotesDomain);

    /**
     * 添加记录
     *
     * @param userId
     */
    List<BbNotesDomain> withdrawalList(int userId);
}
