package com.atjiumi.es.controller;

import com.atjiumi.es.service.EchartsCommodityService;
import com.atjiumi.es.service.impl.EchartsCommodityServiceImpl;
import com.atjiumi.es.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 盛镇林
 * @date 2020/9/18 - 16:48
 */
@RestController
@RequestMapping("/es/echarts-Commodity")
public class EchartsCommodityController {

    @Autowired
    EchartsCommodityServiceImpl echartsCommodityService;

    @RequestMapping("selecCommodityTopFive")
    @CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
    public R selecCommodityTopFive(){
        return  R.ok().data("itmes", echartsCommodityService.selectEchartsCommodity());
    }
}
