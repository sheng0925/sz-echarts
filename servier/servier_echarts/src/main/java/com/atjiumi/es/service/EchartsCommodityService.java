package com.atjiumi.es.service;

import com.atjiumi.es.entity.EchartsCircle;
import com.atjiumi.es.entity.EchartsCommodity;
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
public interface EchartsCommodityService extends IService<EchartsCommodity> {

    //从总记录表中筛选数据
    List<EchartsCommodity> selecCommodityTopFive();

    List<EchartsCommodity> selectEchartsCommodity();
}
