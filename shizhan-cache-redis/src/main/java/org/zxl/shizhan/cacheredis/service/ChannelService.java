package org.zxl.shizhan.cacheredis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.zxl.shizhan.cacheredis.dao.ChannelDAO;
import org.zxl.shizhan.cacheredis.dao.RedisDAO;
import org.zxl.shizhan.cacheredis.entity.TChannel;
import org.zxl.shizhan.cacheredis.util.PageInfo;

import java.util.List;

@Service
public class ChannelService {
    @Autowired
    private ChannelDAO channelDAO;

    @Autowired
    private RedisDAO redisDAO;


    //    键使用默认策略生成，所有参数的hash值  , key = "#tChannel.channelId"
    @Cacheable(cacheNames = "test" )
    public PageInfo<TChannel> query(TChannel tChannel, PageInfo<TChannel> pageInfo){
        System.out.println("query start");
        return channelDAO.query(tChannel, pageInfo);
    }


}
