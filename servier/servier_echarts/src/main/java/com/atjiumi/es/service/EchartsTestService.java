package com.atjiumi.es.service;

import com.atjiumi.es.entity.EchartsTest;
import com.atjiumi.es.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2020-07-17
 */
public interface EchartsTestService extends IService<EchartsTest> {

    public R findAllEcharts();

    /**
     * 查询目的国排行前八的国家
     * @return
     */
    public  List<EchartsTest> selectAll();
}
