package com.atjiumi.es.service;

import com.atjiumi.es.entity.EchartsMap;
import com.atjiumi.es.entity.EchartsNavigation;
import com.atjiumi.es.entity.EchartsOrder;
import com.atjiumi.es.entity.seed.orderTest;
import com.atjiumi.es.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2020-07-17
 */
public interface EchartsOrderService extends IService<EchartsOrder> {

    /**
     * 查询订单，天、月、年
     * @return
     */
    public List<EchartsOrder> selectOrder();


    /**
     * 查询一天的数据
     */
    EchartsOrder selectTestcurrency();

    /**
     * 查询本月的数据
     */
    EchartsOrder selectTestMonth();

    /**
     * 查询今年的数据
     */
    EchartsOrder selectTestOneYear();

    /**
     * 查询去年的数据
     */
    EchartsOrder selectLastYearCurrency();



}
