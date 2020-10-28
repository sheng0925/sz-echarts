package com.atjiumi.es.service.impl;

import com.atjiumi.es.entity.EchartsBrokenLineGraph;
import com.atjiumi.es.mapper.EchartsBrokenLineGraphMapper;
import com.atjiumi.es.service.EchartsBrokenLineGraphService;
import com.atjiumi.es.utils.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EchartsBrokenLineGraphServiceImpl extends ServiceImpl<EchartsBrokenLineGraphMapper,EchartsBrokenLineGraph> implements EchartsBrokenLineGraphService{

    @Autowired
    EchartsBrokenLineGraphService echartsBrokenLineGraphService;

    /**
     * 查询第一个折线图全部
     * @return
     */
    @Override
    public R Queryall() {
        List<List<Integer>> digitTwo =new ArrayList<>();
        List<EchartsBrokenLineGraph> list = echartsBrokenLineGraphService.list(null);
        for (int i =0;i<list.size();i++){
            List<Integer> digit =new ArrayList<>();
            digit.add(list.get(i).getJan());
            digit.add(list.get(i).getFeb());
            digit.add(list.get(i).getMar());
            digit.add(list.get(i).getApr());
            digit.add(list.get(i).getMay());
            digit.add(list.get(i).getJun());
            digit.add(list.get(i).getJul());
            digit.add(list.get(i).getAug());
            digit.add(list.get(i).getSep());
            digit.add(list.get(i).getOcts());
            digit.add(list.get(i).getNov());
            digit.add(list.get(i).getDecs());
            digitTwo.add(digit);
            //System.err.println(i);
            //System.err.println(list.get(i));

        }
        return R.ok().data("itmes",digitTwo) ;
    }
}
