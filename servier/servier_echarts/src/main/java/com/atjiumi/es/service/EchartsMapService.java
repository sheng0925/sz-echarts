package com.atjiumi.es.service;

import com.atjiumi.es.entity.EchartsMap;
import com.atjiumi.es.entity.EchartsTest;
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
public interface EchartsMapService extends IService<EchartsMap> {

    public R findAllEchartsMap();

    /**
     * 查询当天一共去过多少个国家
     */
    List<Object> selectTodayPod();
}
