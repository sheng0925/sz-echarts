package com.atjiumi.es.service;

import com.atjiumi.es.entity.EchartsMap;
import com.atjiumi.es.entity.EchartsPie;
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
public interface EchartsPieService extends IService<EchartsPie> {
    public R findall();

    /**
     * 查询运输排行前三名
     * @return
     */
    public List<EchartsPie> selectPieTopThree();
}
