package com.atjiumi.es.mapper;

import com.atjiumi.es.entity.EchartsMap;
import com.atjiumi.es.entity.EchartsTest;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-07-17
 */
public interface EchartsMapMapper extends BaseMapper<EchartsMap> {

    /**
     * 查询六天前的数据目的国信息
     * @return
     */
    List<EchartsMap> selectTodayPod();

    /**
     * 插入数据到地图表中
     */
    int insertEchartsMaps(List<EchartsMap> list );

    /**
     * 删除地图表中所有数据
     */
    int deleteEchartsMaps();

    /**
     * 查询订单表中所有数据
     */
    List<EchartsMap> selectEchartsMaps();

}
