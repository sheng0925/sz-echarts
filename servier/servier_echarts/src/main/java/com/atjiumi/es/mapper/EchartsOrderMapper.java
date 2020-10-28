package com.atjiumi.es.mapper;

import com.atjiumi.es.entity.EchartsMap;
import com.atjiumi.es.entity.EchartsOrder;
import com.atjiumi.es.entity.seed.orderTest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-07-17
 */
public interface EchartsOrderMapper extends BaseMapper<EchartsOrder> {



    /**
     * 查询一天的
     * @return
     */
     List<EchartsOrder> selectTestcurrency();

    /**
     * 近一个月的
     */
     List<EchartsOrder> selectTestMonth();

    /**
     * 查询今年全年的数据
     */
    List<EchartsOrder> selectTestOneYear();

    /**
     * 查询去年的数据
     * @return
     */
    List<EchartsOrder> selectLastYearCurrency();


    /**
     * 添加到订单表中
     * @param list
     * @return
     */
    int InsertEchartsOrdert(List<EchartsOrder> list);

    /**
     * 删除订单表中所有数据
     */

    int deleteEchartsOrder();


    /**
     * 查询订单表中的数据
     */
    List<EchartsOrder> selectEchartsOrder();


}
