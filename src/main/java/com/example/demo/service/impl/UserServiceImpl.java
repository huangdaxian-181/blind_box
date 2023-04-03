package com.example.demo.service.impl;

import com.example.demo.domain.BbNotesDomain;
import com.example.demo.domain.BbUserDomain;
import com.example.demo.mapper.BbNotesMapper;
import com.example.demo.mapper.BbUserMapper;
import com.example.demo.service.UserService;
import com.example.demo.tools.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author 搬砖的码农
 * @date 2023/03/20
 * @email
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    BbUserMapper bbUserMapper;

    @Autowired
    BbNotesMapper bbNotesMapper;

    @Override
    public int register(BbUserDomain bbUserDomain) {
        BbUserDomain find = bbUserMapper.findOneUser(bbUserDomain.getPhone());

        if (find == null) {
            //注册用户信息进入数据库
            bbUserDomain.setCreate_time(new Date());

            boolean bool = bbUserMapper.addUser(bbUserDomain);

            return 200;
        }

        return 1006;
    }

    @Override
    public String login(BbUserDomain bbUserDomain) {
        BbUserDomain find = bbUserMapper.findOneUser(bbUserDomain.getPhone());

        if (find.getPwd().equals(bbUserDomain.getPwd())) {
            String token = Token.createToken(find.getUserId());

            return token;
        }

        return "";


    }

    @Override
    public BbUserDomain userInfo(int userId) {
        BbUserDomain info = bbUserMapper.userInfo(userId);

        return info;
    }

    @Transactional(isolation = Isolation.DEFAULT, timeout = 3000)
    @Override
    public boolean withdrawal(int userId, Object moeny) {
        try {
            //先扣除用户的提现余额
            boolean bool = bbUserMapper.subtract(userId, moeny);

            if (bool) {
                //添加数据到提现记录表
                BbNotesDomain bbNotesDomain = new BbNotesDomain();

                bbNotesDomain.setUser_id(userId);

                bbNotesDomain.setMoney(String.valueOf(moeny));

                bbNotesDomain.setType(1);

                bbNotesDomain.setCreateTime(new Date());


                bbNotesMapper.addNotes(bbNotesDomain);

                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<BbNotesDomain> withdrawalList(int userId) {
        return bbNotesMapper.withdrawalList(userId);
    }
}
