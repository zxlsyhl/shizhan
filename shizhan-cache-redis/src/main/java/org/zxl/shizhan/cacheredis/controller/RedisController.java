package org.zxl.shizhan.cacheredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zxl.shizhan.cacheredis.service.RedisService;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisService redisService;


    @GetMapping("/get")
    public Object get(@RequestParam String key){
        Object obj = redisService.get(key);
        System.out.println("key:"+key+";obj:"+obj);
        return obj;
    }



}
