package com.atjiumi.es.service.impl;

import com.atjiumi.es.Config.RemoteProperties;
import com.atjiumi.es.entity.EchartsMap;
import com.atjiumi.es.entity.EchartsPie;
import com.atjiumi.es.mapper.EchartsMapMapper;
import com.atjiumi.es.mapper.EchartsPieMapper;
import com.atjiumi.es.service.EchartsMapService;
import com.atjiumi.es.service.EchartsPieService;
import com.atjiumi.es.utils.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
/*
引入properties文件对象
*/
@EnableConfigurationProperties(RemoteProperties.class)
@Service
public class EchartsPieServiceImpl extends ServiceImpl<EchartsPieMapper, EchartsPie> implements EchartsPieService {
    @Autowired
    private EchartsPieService echartsPieService;

    @Autowired
    RemoteProperties remoteProperties;

    @Autowired
    private EchartsPieMapper echartsPieMapper;
    /**
     * 查询Pie全部
     * @return
     */
    @Override
    public R findall() {
        return R.ok().data("itmes",echartsPieService.list(null));
    }

    @Override
    public List<EchartsPie> selectPieTopThree() {
        //这是从自己的员运输表中查询
        List<EchartsPie> echartsPies = echartsPieMapper.selectTodayTraffic();
        //List<EchartsPie> echartsPies = echartsPieMapper.selectPieTopThree();
        String str = remoteProperties.getDeclareCode();
        String[] split = str.split(",");
         for(EchartsPie e :echartsPies){
            for (int i =0;i<split.length;i++){
                if (split[i].substring(0,1).equals(e.getName())){
                    e.setName(split[i].substring(1,split[i].length()));
                    break;
                }
            }
        }
        return echartsPies;
    }
}
