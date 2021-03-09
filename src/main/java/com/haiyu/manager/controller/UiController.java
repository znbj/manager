package com.haiyu.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName UiController
 * @Author znb
 * @Date 2021-03-07 19:19
 * @Description UiController
 * @Version 1.0
 */
@Controller
public class UiController {

    @RequestMapping("bta/user")
    public String tologin(){
        return "bta/WalkUserManage";
    }
}
