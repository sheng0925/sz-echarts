package com.atjiumi.es.controller;

import com.atjiumi.es.service.impl.EchartsBrokenLineGraphTwoServiceImpl;
import com.atjiumi.es.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/es/echarts-BrokenLineGraphTwo")
public class EchartsBrokenLineGraphTwoController {

    @Autowired
    EchartsBrokenLineGraphTwoServiceImpl echartsBrokenLineGraphTwoService;


    @RequestMapping("findAll")
    @CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
    public R findAll(){

        return R.ok().data("itmes",echartsBrokenLineGraphTwoService.selectEchartsBrokenlineGraphTwo());
    }


}
