package com.atjiumi.es.service.impl;

import com.atjiumi.es.entity.EchartsCommodity;
import com.atjiumi.es.entity.EchartsMapChart;
import com.atjiumi.es.mapper.EchartsCommodityMapper;
import com.atjiumi.es.mapper.EchartsMapChartMapper;
import com.atjiumi.es.service.EchartsCommodityService;
import com.atjiumi.es.service.EchartsMapChartService;
import com.atjiumi.es.utils.QuChongFuUtils;
import com.atjiumi.es.utils.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class EchartsCommodityServiceImpl extends ServiceImpl<EchartsCommodityMapper, EchartsCommodity> implements EchartsCommodityService {
    @Autowired
    EchartsCommodityMapper echartsCommodityMapper;

    @Override
    public List<EchartsCommodity> selecCommodityTopFive() {
        QuChongFuUtils qcfu =new QuChongFuUtils();
        //List<EchartsCommodity> echartsCommodities = echartsCommodityMapper.selectEchartsCommodity();
        List<EchartsCommodity> echartsCommodities = echartsCommodityMapper.selecCommodityTopFive();
        String str ="";
        for (EchartsCommodity e :echartsCommodities){
            str+=e.getName()+",";
        }

        String[] split = str.split(",");
        List array = qcfu.array(split, new ArrayList());
        List<EchartsCommodity> listCommodity=new ArrayList<>();
        for(int i =0;i<10;i++){
            listCommodity.add((EchartsCommodity)array.get(i));
        }
        return listCommodity;
    }

    @Override
    public List<EchartsCommodity> selectEchartsCommodity() {
        return echartsCommodityMapper.selectEchartsCommodity();
    }
}
