package com.atjiumi.es.controller;


import com.atjiumi.es.service.impl.EchartsTestServiceImpl;
import com.atjiumi.es.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/es/echarts-test")
public class EchartsTestController {

    @Autowired(required = true)
    private EchartsTestServiceImpl echartsTestService;

    @RequestMapping("findAll")
    @CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
    public R findAllEcharts(){
        return R.ok().data("itmes",echartsTestService.selectAll());
    }








}

