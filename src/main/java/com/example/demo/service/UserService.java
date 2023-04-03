package com.example.demo.service;

import com.example.demo.domain.BbNotesDomain;
import com.example.demo.domain.BbUserDomain;

import java.util.List;

/**
 * 用户接口
 *
 * @author huangdaxian
 * @date 2023/03/20
 * @email 3081476398@qq.com
 **/
public interface UserService {
    /**
     * 注册用户
     *
     * @param bbUserDomain
     */
    int register(BbUserDomain bbUserDomain);

    /**
     * 登录用户
     *
     * @param bbUserDomain
     */
    String login(BbUserDomain bbUserDomain);

    /**
     * 用户详细信息
     *
     * @param userId
     */
    BbUserDomain userInfo(int userId);

    /**
     * 用户提现
     *
     * @param userId
     * @param moeny
     */
    boolean withdrawal(int userId, Object moeny);

    /**
     * 提现列表
     *
     * @param userId
     */
    List<BbNotesDomain> withdrawalList(int userId);
}
