package com.atjiumi.es.service;

import com.atjiumi.es.entity.EchartsMap;
import com.atjiumi.es.entity.EchartsMapChart;
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
public interface EchartsMapChartService extends IService<EchartsMapChart> {

    public R findall();

    /**
     * 查询当天的数据，申报港口，币制，总金额，总重量，以及没个订单的数量不能重复
     * @return
     */
    public List<EchartsMapChart> selectJInTianData();
}
