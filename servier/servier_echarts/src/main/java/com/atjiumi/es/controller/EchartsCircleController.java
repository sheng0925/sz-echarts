package com.atjiumi.es.controller;


import com.atjiumi.es.entity.EchartsCircle;
import com.atjiumi.es.service.EchartsCircleService;
import com.atjiumi.es.service.impl.EchartsCircleServiceImpl;
import com.atjiumi.es.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/es/echarts-circle")
public class EchartsCircleController {

    @Autowired
    private EchartsCircleServiceImpl echartsCircleService;

    @RequestMapping("queryCircleOne")
    @CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
    public R queryCircleOne(){
       return echartsCircleService.queryCircleOne();
    }


    @RequestMapping("queryCircleTwo")
    @CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
    public R queryCircleTwo(){
       return echartsCircleService.queryCircleTwo();
    }

    @RequestMapping("queryCircleThree")
    @CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
    public R queryCircleThree(){
        return echartsCircleService.queryCircleThree();
    }


    @RequestMapping("queryCircleFour")
    @CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
    public R queryCircleFour(){
        return echartsCircleService.queryCircleFour();
    }

}
