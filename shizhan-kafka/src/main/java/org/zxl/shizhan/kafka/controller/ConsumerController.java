package org.zxl.shizhan.kafka.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zxl.shizhan.kafka.services.Consumer2Service;
import org.zxl.shizhan.kafka.services.ConsumerService;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;
    @Autowired
    private Consumer2Service consumer2Service;

    /**
     * 拉取指定主题和数量的消息
     * @param subscribe
     * @param commitFlag
     * @return
     */
    @GetMapping("/poolMsg")
    public String poolMsg(@RequestParam String subscribe, @RequestParam int commitFlag){
        consumerService.poolMsg(subscribe, commitFlag);
        return "true";
    }
    /**
     * 拉取指定主题和数量的消息
     * @param subscribe
     * @param commitFlag
     * @return
     */
    @GetMapping("/poolMsg2")
    public String poolMsg2(@RequestParam String subscribe, @RequestParam int commitFlag){
        consumer2Service.poolMsg(subscribe, commitFlag);
        return "true";
    }
}
