package com.atjiumi.es.service;

import com.atjiumi.es.entity.EchartsBrokenLineGraph;
import com.atjiumi.es.entity.EchartsBrokenLineGraphTwo;
import com.atjiumi.es.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface EchartsBrokenLineGraphTwoService extends IService<EchartsBrokenLineGraphTwo> {

    public R findAll();

    /**
     * 在总表中查询折线图历史数据本月，上月十五天数据，并存入到自己的表中
     * @return
     */
    public void selectThisMonthAndLastMonth();

    /**
     * 在历史表中今年的数据，和去年的数据以月位单位
     * @return
     */
    public List<List<Integer>> selectEchartsBrokenlineGraphTwo();
}
