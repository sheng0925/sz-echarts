package com.atjiumi.es.controller;

import com.atjiumi.es.service.impl.EchartsPieServiceImpl;
import com.atjiumi.es.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/es/echarts-pie")
public class EchartsPieController {

        @Autowired(required = true)
        private EchartsPieServiceImpl echartsPieService;

        @RequestMapping("findall")
        @CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
        public R findall(){
                return R.ok().data("itmes",echartsPieService.selectPieTopThree());

        }
}
