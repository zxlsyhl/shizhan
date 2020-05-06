package org.zxl.shizhan.cacheredis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zxl.shizhan.cacheredis.dao.ChannelDAO;
import org.zxl.shizhan.cacheredis.entity.TChannel;
import org.zxl.shizhan.cacheredis.service.ChannelService;
import org.zxl.shizhan.cacheredis.util.PageInfo;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/channel")
public class ChannelController {
    @Autowired
    private ChannelDAO channelDAO;

    @GetMapping("/getall")
    public List<TChannel> getall(){
        return channelDAO.getChannels();
    }

    @PostMapping("/add")
    public void add(@RequestBody TChannel tChannel){
        Date now = new Date();
        tChannel.setCreateDate(now);
        tChannel.setUpdateDate(now);
        channelDAO.add(tChannel);
    }

    @Autowired
    private ChannelService channelService;

    private static Logger logger = LoggerFactory.getLogger(ChannelService.class);

    @GetMapping("/query")
    public PageInfo<TChannel> query(@RequestParam(required = false) Long channelId, @RequestParam(required = false) String channelName,@RequestParam(defaultValue = "1")  long pageNo,@RequestParam(defaultValue = "10")  long pageSize){
        logger.debug("query start");
        TChannel tChannel = new TChannel();
        tChannel.setChannelId(channelId);
        tChannel.setChannelName(channelName);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNo(pageNo);
        pageInfo.setPageSize(pageSize);
        return channelService.query(tChannel, pageInfo);
    }

}
