package com.atjiumi.es.utils;

import com.atjiumi.es.Config.RemoteProperties;
import com.atjiumi.es.entity.*;
import com.atjiumi.es.mapper.*;
import com.atjiumi.es.service.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TimingUpDateData {
    //订单
    @Autowired
    EchartsOrderServiceImpl echartsOrderService;
    @Autowired
    EchartsOrderMapper echartsOrderMapper;

    //2.导航栏历史数据
    @Autowired
    EchartsNavigationServiceImpl echartsNavigationService;
    @Autowired
    EchartsNavigationMapper echartsNavigationMapper;

    //3.目的国数据
    @Autowired
    EchartsTestServiceImpl echartsTestService;
    @Autowired
    EchartsTestMapper echartsTestMapper;

    //4.世界地图
    @Autowired
    EchartsMapServiceImpl echartsMapService;
    @Autowired
    RemoteProperties remoteProperties;
    @Autowired
    EchartsMapMapper echartsMapMapper;

    //5.热销商品排行
    @Autowired
    EchartsCommodityServiceImpl echartsCommodityService;
    @Autowired
    EchartsCommodityMapper echartsCommodityMapper;

    //6.历史数据
    @Autowired
    EchartsBrokenLineGraphTwoMapper echartsBrokenLineGraphTwoMapper;
    @Autowired
    EchartsBrokenLineGraphTwoServiceImpl echartsBrokenLineGraphTwoServicel;

    //7.运输方式
    @Autowired
    EchartsPieMapper echartsPieMapper;

    //8.贸易方式
    @Autowired
    EchartsMapChartMapper echartsMapChartMapper;



    //初始化类信息，打印日志时候就能知道是在那个类打印的
    protected static final Logger logger = LoggerFactory.getLogger(TimingUpDateData.class);


    public void UpdateAll(){
        /**
         * 1订单
         */
        List<EchartsOrder> list =new ArrayList<>();
//        //查询最新的数据存入集合中
        list.add(echartsOrderService.selectTestcurrency());
        list.add(echartsOrderService.selectTestMonth());
        list.add(echartsOrderService.selectTestOneYear());
        list.add(echartsOrderService.selectLastYearCurrency());
        if(list!=null&list.size()>0){
            //删除之前的数据
            echartsOrderMapper.deleteEchartsOrder();
            echartsOrderMapper.InsertEchartsOrdert(list);
        }else {
            logger.info("总记录表中没有拿到数据，关于订单的");
        }

        /**
         * 2导航栏历史数据
         */
        List<EchartsNavigation> Navlist = echartsNavigationService.selectHistoryData();
        if (Navlist!=null&list.size()>0){
            //删除之前的数据
            echartsNavigationMapper.deleteEchartsEchartsNavigation();
            echartsNavigationMapper.insertEchartsEchartsNavigation(Navlist);
        }else{
        logger.info("总记录表中没有拿到数据，关于导航栏的数据");
    }


        /**
         * 3目的国数据
         */
        List<EchartsTest> echartsTests = echartsTestService.selectAll();
        if (echartsTests!=null&echartsTests.size()>0){
            echartsTestMapper.deleteEchartsTest();
            echartsTestMapper.insertEchartsTest(echartsTests);
        }else {
            logger.info("总记录表中没有拿到数据，关于目的国数据");
        }


        /**
         * 4世界地图
         */
        String str =remoteProperties.getStreet();
        //目的国编码对应的国家数组
        String[] split = str.split(",");
        List<EchartsMap> echartsMaps = echartsMapMapper.selectTodayPod();
        for(int j =0;j<echartsMaps.size();j++){
            for(int i =0;i<split.length;i++){
                if (split[i].substring(0,3).equals(echartsMaps.get(j).getName())){
                    echartsMaps.get(j).setName("深圳");
                    echartsMaps.get(j).setDestination(split[i].substring(3,split[i].length()));
                    break;
                }
            }
        }
        if (echartsMaps!=null&echartsMaps.size()>0){
            echartsMapMapper.deleteEchartsMaps();
            echartsMapMapper.insertEchartsMaps(echartsMaps);
        }else {
        logger.info("总记录表中没有拿到数据，关于地图的数据");
        }


        /**
         * 5热销商品排行
         */
        List<EchartsCommodity> echartsCommodities = echartsCommodityService.selecCommodityTopFive();
        if (echartsCommodities!=null&echartsCommodities.size()>0){
            echartsCommodityMapper.deleteEchartsCommodity();
            echartsCommodityMapper.insertEchartsCommodity(echartsCommodities);
        }else {
            logger.info("总记录表中没有拿到数据，关于热销商品");
        }


        /**
         * 6历史数据
         */
//        调用方法将总表的数据存入到折现图历史表中
        echartsBrokenLineGraphTwoServicel.selectThisMonthAndLastMonth();

        /**
         * 7运输方式
         */
        List<EchartsPie> echartsPies = echartsPieMapper.selectPieTopThree();
        if (echartsPies!=null&echartsPies.size()>0) {
            echartsPieMapper.deletePieAll();
            echartsPieMapper.INSERTPieTodayTop(echartsPies);
        }else {
        logger.info("总记录表中没有拿到数据，关于运输方式");
        }
        /**
         * 8贸易方式
         */
        List<EchartsMapChart> echartsMapCharts = echartsMapChartMapper.selectJInTianData();

        if (echartsMapCharts!=null&echartsMapCharts.size()>0){
            echartsMapChartMapper.deleteMapChartAll();
            echartsMapChartMapper.INSERTMapChartTodayTop(echartsMapCharts);
        }else {
        logger.info("总记录表中没有拿到数据，关于销售比例");
    }
    }

}
