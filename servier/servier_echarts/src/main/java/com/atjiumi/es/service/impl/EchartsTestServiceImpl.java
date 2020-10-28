package com.atjiumi.es.service.impl;

import com.atjiumi.es.Config.RemoteProperties;
import com.atjiumi.es.entity.EchartsTest;
import com.atjiumi.es.mapper.EchartsTestMapper;
import com.atjiumi.es.service.EchartsTestService;
import com.atjiumi.es.utils.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.script.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
public class EchartsTestServiceImpl extends ServiceImpl<EchartsTestMapper, EchartsTest> implements EchartsTestService {
    @Autowired
    private EchartsTestService echartsTestService;

    @Autowired
    RemoteProperties remoteProperties;

    @Autowired
    private EchartsTestMapper echartsTestMapper;
    /**
     * 查询Test全部
     * @return
     */
    @Override
    public R findAllEcharts() {
        List<EchartsTest> echartsTests = echartsTestService.list(null);
        return R.ok().data("itmes",echartsTests);
    }


    public List<EchartsTest> selectAll() {
        String str = remoteProperties.getStreet();
        //目的国编码对应的国家数组
        String[] split = str.split(",");
        //目的地货品数量排行前八
       // List<EchartsTest> echartsTests = echartsTestMapper.selectEchartsTest();
        List<EchartsTest> echartsTests = echartsTestMapper.selectAll();
        for(int j =0;j<echartsTests.size();j++){
            for(int i =0;i<split.length;i++){
                if (split[i].substring(0,3).equals(echartsTests.get(j).getName())){
                    echartsTests.get(j).setName(split[i].substring(3,split[i].length()));
                    //获取当天的数据，数据比较少不用除以10000
                    echartsTests.get(j).setValue(echartsTests.get(j).getValue()/10000);
                    break;
                }
            }
        }
        return echartsTests;
    }
}
