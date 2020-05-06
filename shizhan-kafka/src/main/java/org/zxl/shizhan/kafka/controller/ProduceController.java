package org.zxl.shizhan.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zxl.shizhan.kafka.services.ProduceService;

@RestController
@RequestMapping("/produce")
public class ProduceController {
    @Autowired
    private ProduceService produceService;

    /**
     *
     * @param subscribe
     * @param key 使用key值求hash来确定分片
     * @param value
     * @return
     */
    @PostMapping("/sendMsg")
    public String sendMsg(@RequestParam String subscribe, @RequestParam String key,@RequestParam String value){
        produceService.sendMsg(subscribe, key, value);
        return "0000";
    }
}
