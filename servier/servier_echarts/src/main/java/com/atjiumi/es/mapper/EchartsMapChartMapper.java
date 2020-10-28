package com.atjiumi.es.mapper;

import com.atjiumi.es.entity.EchartsMap;
import com.atjiumi.es.entity.EchartsMapChart;
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
public interface EchartsMapChartMapper extends BaseMapper<EchartsMapChart> {

    /**
     * 查询当天的数据，申报港口，币制，总金额，总重量，以及每个订单的数量不能重复
     * @return
     */
    List<EchartsMapChart> selectJInTianData();

    /**
     * 将今天贸易额添加到贸易额表中
     * @param list
     * @return
     */
    int INSERTMapChartTodayTop(List<EchartsMapChart> list);


    /**
     * 删除贸易表中所有数据
     * @return
     */
    int deleteMapChartAll();


    /**
     * 查询贸易表中今天的各个港区贸易额度
     * @return
     */
    List<EchartsMapChart> selectEcsTransactionAmount();
}
