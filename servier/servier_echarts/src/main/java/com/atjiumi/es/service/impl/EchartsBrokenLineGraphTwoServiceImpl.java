package com.atjiumi.es.service.impl;

import com.atjiumi.es.entity.EchartsBrokenLineGraph;
import com.atjiumi.es.entity.EchartsBrokenLineGraphTwo;
import com.atjiumi.es.entity.seed.BrokenLineGraphTwoSubclass;
import com.atjiumi.es.mapper.EchartsBrokenLineGraphMapper;
import com.atjiumi.es.mapper.EchartsBrokenLineGraphTwoMapper;
import com.atjiumi.es.service.EchartsBrokenLineGraphService;
import com.atjiumi.es.service.EchartsBrokenLineGraphTwoService;
import com.atjiumi.es.utils.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EchartsBrokenLineGraphTwoServiceImpl extends ServiceImpl<EchartsBrokenLineGraphTwoMapper, EchartsBrokenLineGraphTwo> implements EchartsBrokenLineGraphTwoService {

    @Autowired
    private EchartsBrokenLineGraphTwoService echartsBrokenLineGraphTwoService;

    @Autowired
    private EchartsBrokenLineGraphTwoMapper echartsBrokenLineGraphTwoMapper;
    /**
     * 查询第二个折线图全部
     * @return
     */
    @Override
    public R findAll() {
        return R.ok().data("itmes",null);
    }

    protected static final  Logger logger = LoggerFactory.getLogger(EchartsBrokenLineGraphTwoServiceImpl.class);
    @Override
    public void selectThisMonthAndLastMonth() {
        //查询今年
        List<EchartsBrokenLineGraphTwo> Twos = echartsBrokenLineGraphTwoMapper.selectBrokenLineTwo();
        //查询去年
        List<EchartsBrokenLineGraphTwo> Twos1 = echartsBrokenLineGraphTwoMapper.selectLastMonth();
        if (Twos!=null&Twos.size()>0&Twos1!=null&Twos1.size()>0) {
            //将本表的数据删除
            echartsBrokenLineGraphTwoMapper.deleteEchartsBrokenLineTwo();
            //分别将今年和去年的数据存入表中
            echartsBrokenLineGraphTwoMapper.insertEchartsBrokenLineTwo(Twos);
            echartsBrokenLineGraphTwoMapper.insertEchartsBrokenLineTwo(Twos1);
        }else {
            logger.info("没有从总记录表中获取数据，关于历史数据");
        }

    }
    @Override
    public List<List<Integer>> selectEchartsBrokenlineGraphTwo() {
        List<EchartsBrokenLineGraphTwo> list = echartsBrokenLineGraphTwoMapper.selectEchartsBrokenlineTwoTwentyNineteen();
        List<Integer> li2 =new ArrayList<>();
        for (EchartsBrokenLineGraphTwo b :list){
            li2.add(b.getValue());
        }
        List<EchartsBrokenLineGraphTwo> list1 = echartsBrokenLineGraphTwoMapper.selectEchartsBrokenlineTwoTwentyTwenty();
        List<Integer> li =new ArrayList<>();
        for (EchartsBrokenLineGraphTwo b :list1){
            li.add(b.getValue());
        }
        //定义集合返回数据
        List<List<Integer>> AllList=new ArrayList<>();
        AllList.add(li2);
        AllList.add(li);
        return AllList;
    }
}
