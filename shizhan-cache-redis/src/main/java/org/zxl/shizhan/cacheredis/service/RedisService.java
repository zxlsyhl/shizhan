package org.zxl.shizhan.cacheredis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zxl.shizhan.cacheredis.dao.ChannelDAO;
import org.zxl.shizhan.cacheredis.dao.RedisDAO;

@Service
public class RedisService {
    @Autowired
    private RedisDAO redisDAO;

    public Object get(String  key){
        return redisDAO.get(key);
    }
}
