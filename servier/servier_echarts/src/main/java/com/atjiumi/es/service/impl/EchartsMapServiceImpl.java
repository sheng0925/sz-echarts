package com.atjiumi.es.service.impl;

import com.atjiumi.es.Config.RemoteProperties;
import com.atjiumi.es.entity.EchartsMap;
import com.atjiumi.es.entity.EchartsTest;
import com.atjiumi.es.mapper.EchartsMapMapper;
import com.atjiumi.es.service.EchartsMapService;
import com.atjiumi.es.utils.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.script.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
public class EchartsMapServiceImpl extends ServiceImpl<EchartsMapMapper, EchartsMap> implements EchartsMapService {

    @Autowired
    private EchartsMapService echartsMapService;

    @Autowired
    RemoteProperties remoteProperties;

    @Autowired
    private EchartsMapMapper echartsMapMapper;
    /**
     * 查询Map全部地图
     * @return
     */
    @Override
    public R findAllEchartsMap() {
        //定义集合存放返回的集合
        List<List<EchartsMap>> data =new ArrayList<>();

        List<EchartsMap> list = echartsMapService.list(null);
        for (int i=0;i<list.size();i++){
            List<EchartsMap> echartsMaplist =new ArrayList<>();
            //设置航线的开始飞行的地点
            EchartsMap echartsMap  =new EchartsMap(list.get(i).getDestination(),null,null);
            echartsMaplist.add(echartsMap);
            echartsMaplist.add(new EchartsMap(list.get(i).getName(),list.get(i).getValue(),null));
            data.add(echartsMaplist);
        }
        List<Object> finalgether =new ArrayList<>();
        finalgether.add("深圳");
        finalgether.add(data);
        return R.ok().data("itmes",finalgether);
    }




    public List<Object> selectTodayPod(){
        //定义集合存放返回的集合
        List<List<EchartsMap>> data =new ArrayList<>();
//        String str =remoteProperties.getStreet();
//        //目的国编码对应的国家数组
//        String[] split = str.split(",");
//        List<EchartsMap> echartsMaps = echartsMapMapper.selectTodayPod();
//        for(int j =0;j<echartsMaps.size();j++){
//            for(int i =0;i<split.length;i++){
//                if (split[i].substring(0,3).equals(echartsMaps.get(j).getName())){
//                    echartsMaps.get(j).setName("深圳");
//
//                    echartsMaps.get(j).setDestination(split[i].substring(3,split[i].length()));
//                    break;
//                }
//            }
//        }
        /**
         * 调用自己表中的数据
         */
        List<EchartsMap> echartsMaps = echartsMapMapper.selectEchartsMaps();

        for (int i=0;i<echartsMaps.size();i++){
            List<EchartsMap> echartsMaplist =new ArrayList<>();
            //设置航线的开始飞行的地点
            EchartsMap echartsMap  =new EchartsMap(echartsMaps.get(i).getDestination(),null,null);
            echartsMaplist.add(echartsMap);
            echartsMaplist.add(new EchartsMap(echartsMaps.get(i).getName(),echartsMaps.get(i).getValue(),null));
            data.add(echartsMaplist);
        }
        List<Object> finalgether =new ArrayList<>();
        finalgether.add("深圳");
        finalgether.add(data);
        return finalgether;

    }


}
