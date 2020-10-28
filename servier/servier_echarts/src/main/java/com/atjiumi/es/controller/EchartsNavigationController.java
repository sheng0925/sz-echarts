package com.atjiumi.es.controller;
import com.atjiumi.es.utils.R;
import com.atjiumi.es.service.impl.EchartsNavigationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("es/es-chartsNavigation")
public class EchartsNavigationController {

    @Autowired
    EchartsNavigationServiceImpl echartsNavigationService;

    @RequestMapping("findAllEchartsNavigation")
    @CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
    private R findAllEchartsNavigation(){
        return R.ok().data("itmes",echartsNavigationService.selectEchartsHistoryData());
    }
}
