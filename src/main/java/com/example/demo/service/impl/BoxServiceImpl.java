package com.example.demo.service.impl;

import com.example.demo.domain.BbBoxDomain;
import com.example.demo.domain.BbImageDomain;
import com.example.demo.domain.BbPaperDomain;
import com.example.demo.mapper.BbBoxsMapper;
import com.example.demo.mapper.BbImageMapper;
import com.example.demo.mapper.BbPaperMapper;
import com.example.demo.service.BoxService;
import com.example.demo.tools.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 搬砖的码农
 * @date 2023/03/18
 * @email
 **/
@Service
public class BoxServiceImpl implements BoxService {

    @Autowired
    BbBoxsMapper bbBoxMapper;

    @Autowired
    BbPaperMapper bbPaperMapper;

    @Autowired
    BbImageMapper bbImageMapper;

    @Override
    public List<BbBoxDomain> getList(int userId, int type) {
        List<BbBoxDomain> list = bbBoxMapper.findUserAll(userId, type);

        return list;
    }

    @Override
    public List<BbPaperDomain> getOneList(int userId) {

        List<BbPaperDomain> list = bbPaperMapper.findOne(userId);

        return list;
    }

    @Override
    public boolean addBox(BbBoxDomain bbBoxDomain, String order_no) {
        //订单编号
        bbBoxDomain.setOrder_no(order_no);

        bbBoxDomain.setCreate_time(new Date());

        //订单号
        bbBoxDomain.setType(3);

        boolean bool = bbBoxMapper.addBox(bbBoxDomain);

        BbImageDomain bbImageDomain = new BbImageDomain();

        //图片添加到数据库
        for (int i = 0; i < bbBoxDomain.getImg().length; i++) {

            bbImageDomain.setBox_id(bbBoxDomain.getBox_id());
            bbImageDomain.setUrl(bbBoxDomain.getImg()[i]);
            bbImageDomain.setCreate_time(new Date());

            bbImageMapper.addImg(bbImageDomain);
        }

        return bool;
    }

    @Override
    public boolean delBox(int box_id) {
        return bbBoxMapper.delBox(box_id);
    }
}
