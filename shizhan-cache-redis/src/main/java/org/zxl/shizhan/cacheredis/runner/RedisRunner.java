package org.zxl.shizhan.cacheredis.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.zxl.shizhan.cacheredis.dao.RedisDAO;
import org.zxl.shizhan.cacheredis.entity.TChannel;

@Component
public class RedisRunner implements ApplicationRunner {

    private static Logger logger = LoggerFactory.getLogger(RedisRunner.class);

    @Autowired
    private RedisDAO redisDAO;

    @Override
    public void run(ApplicationArguments args) {
        logger.debug("RedisRunner start ***********************");

        redisDAO.set("zxlong", "1212");

        TChannel tChannel = new TChannel();
        tChannel.setChannelId(2);
        tChannel.setChannelName("33333333");
        redisDAO.set("channel", tChannel);
        logger.debug("RedisRunner end ***********************");

    }
}
