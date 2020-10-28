package com.atjiumi.es.service;

import com.atjiumi.es.entity.EchartsMap;
import com.atjiumi.es.entity.EchartsNavigation;
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
public interface EchartsNavigationService extends IService<EchartsNavigation> {

    public R findAllEchartsNavigation();


    /**
     * 查询历史申报车次，按照深圳机场快件，前海保税
     */
    public List<EchartsNavigation> selectHistoryData();

    /**
     * 在自定义表总查询全部
     * @return
     */
    public List<EchartsNavigation> selectEchartsHistoryData();

}
