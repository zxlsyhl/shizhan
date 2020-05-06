package org.zxl.shizhan.cacheredis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.zxl.shizhan.cacheredis.entity.User;
import org.zxl.shizhan.cacheredis.util.Result;

@RestController
@RequestMapping("/log")
@CrossOrigin
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);
    @PostMapping("/loginIn")
    public Result loginIn(@RequestBody User user){
        logger.debug("user.toString():{}",user.toString());
        Result result = new Result();
        result.setCode("200");
        result.setMsg("0000");
        return result;
    }
}
