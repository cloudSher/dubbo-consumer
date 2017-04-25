package com.sher.boot.controller;

import com.sher.boot.domain.FrSetting;
import com.sher.boot.handler.FrHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wei.zhao on 2017/4/25.
 */
@RestController
@RequestMapping("/fr")
public class FrController {

    @Autowired
    FrHandler frHandler;

    @RequestMapping("/setting")
    public String setting(FrSetting setting){
        if(setting != null){
           return frHandler.doHandle(setting);
        }
        return "";
    }
}
