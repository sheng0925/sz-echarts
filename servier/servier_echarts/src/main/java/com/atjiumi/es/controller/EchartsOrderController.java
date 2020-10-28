package com.atjiumi.es.controller;

import com.atjiumi.es.service.impl.EchartsNavigationServiceImpl;
import com.atjiumi.es.service.impl.EchartsOrderServiceImpl;
import com.atjiumi.es.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("es/es-chartsOrder")
public class EchartsOrderController {

    @Autowired
    EchartsOrderServiceImpl echartsOrderService;

    @RequestMapping("findAllEchartsOrder")
    @CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
    private R findAllEchartsOrder(){
        return R.ok().data("itmes",echartsOrderService.selectOrder());
    }
}
