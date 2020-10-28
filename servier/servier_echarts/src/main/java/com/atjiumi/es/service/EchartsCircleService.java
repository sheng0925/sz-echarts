package com.atjiumi.es.service;

import com.atjiumi.es.entity.EchartsCircle;
import com.atjiumi.es.entity.EchartsPie;
import com.atjiumi.es.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2020-07-17
 */
public interface EchartsCircleService extends IService<EchartsCircle> {


    public R queryCircleOne();

    public R queryCircleTwo();

    public R queryCircleThree();

    public R queryCircleFour();
}
