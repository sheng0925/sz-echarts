package com.atjiumi.es.controller;

import com.atjiumi.es.entity.EchartsMap;
import com.atjiumi.es.service.EchartsMapService;
import com.atjiumi.es.service.impl.EchartsMapServiceImpl;
import com.atjiumi.es.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/es/echarts-map")
public class EhcartsMapcontroller {
        @Autowired
        private EchartsMapServiceImpl echartsMapService;

        @CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")//解决跨域问题
        @RequestMapping("findall")
        public R findAllEchartsMap(){
          return R.ok().data("itmes",echartsMapService.selectTodayPod());
        }

}
