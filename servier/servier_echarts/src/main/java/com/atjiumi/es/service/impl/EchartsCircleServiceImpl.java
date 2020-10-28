package com.atjiumi.es.service.impl;

import com.atjiumi.es.entity.EchartsCircle;
import com.atjiumi.es.entity.EchartsPie;
import com.atjiumi.es.mapper.EchartsCircleMapper;
import com.atjiumi.es.mapper.EchartsPieMapper;
import com.atjiumi.es.service.EchartsCircleService;
import com.atjiumi.es.service.EchartsPieService;
import com.atjiumi.es.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-07-17
 */
@Service
public class EchartsCircleServiceImpl extends ServiceImpl<EchartsCircleMapper, EchartsCircle> implements EchartsCircleService {

    @Autowired
    private EchartsCircleService echartsCircleService;

    /**
     * 查询第一个饼状
     * @return
     */
    @Override
    public R queryCircleOne() {
        QueryWrapper<EchartsCircle> Wrapper = new QueryWrapper<>();
        Wrapper.eq("circle_one",null);
        List<EchartsCircle> list = echartsCircleService.list(null);
        //System.err.println(list.get(0));
        return R.ok().data("itmes",list.get(0).getCircleOne());
    }

    /**
     * 查询第二个饼状
     * @return
     */
    @Override
    public R queryCircleTwo() {
        QueryWrapper<EchartsCircle> Wrapper = new QueryWrapper<>();
        Wrapper.eq("circle_one",null);
        List<EchartsCircle> list = echartsCircleService.list(null);
        //System.err.println(list);
        return R.ok().data("itmes",list.get(0).getCircleTwo());
    }

    /**
     * 查询第三个饼状
     * @return
     */
    @Override
    public R queryCircleThree() {
        QueryWrapper<EchartsCircle> Wrapper = new QueryWrapper<>();
        Wrapper.eq("circle_one",null);
        List<EchartsCircle> list = echartsCircleService.list(null);
        //System.err.println(list);
        return R.ok().data("itmes",list.get(0).getCircleThree());
    }

    /**
     * 查询第四个饼状
     * @return
     */
    @Override
    public R queryCircleFour() {
        QueryWrapper<EchartsCircle> Wrapper = new QueryWrapper<>();
        Wrapper.eq("circle_one",null);
        List<EchartsCircle> list = echartsCircleService.list(null);
        //System.err.println(list);
        return R.ok().data("itmes",list.get(0).getCircleFour());
    }
}
