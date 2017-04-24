package com.sher.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wei.zhao on 2017/3/28.
 */

@RestController
@RequestMapping("/")
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
