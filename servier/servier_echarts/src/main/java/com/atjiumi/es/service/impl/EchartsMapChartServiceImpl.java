package com.atjiumi.es.service.impl;

import com.atjiumi.es.Config.RemoteProperties;
import com.atjiumi.es.entity.EchartsMap;
import com.atjiumi.es.entity.EchartsMapChart;
import com.atjiumi.es.mapper.EchartsMapChartMapper;
import com.atjiumi.es.mapper.EchartsMapMapper;
import com.atjiumi.es.service.EchartsMapChartService;
import com.atjiumi.es.service.EchartsMapService;
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
@EnableConfigurationProperties(RemoteProperties.class)
@Service
public class EchartsMapChartServiceImpl extends ServiceImpl<EchartsMapChartMapper, EchartsMapChart> implements EchartsMapChartService {

    @Autowired
    private EchartsMapChartService echartsMapChartService;

    @Autowired
    RemoteProperties remoteProperties;

    @Autowired
    private EchartsMapChartMapper echartsMapChartMapper;


    /**
     * 查询全部
     * @return
     */
    @Override
    public R findall() {
        List<EchartsMapChart> list = echartsMapChartService.list(null);
        return R.ok().data("itmes",list);
    }


    @Override
    public List<EchartsMapChart> selectJInTianData() {
        //币制对应编码
        String currency ="142人民币,502美元,116日本元,132新加坡元";
        //申报地对应编码
        String customsCode =remoteProperties.getCustoms();
        String[] currencys = currency.split(",");
        String[] customsCodes = customsCode.split(",");
        //List<EchartsMapChart> echartsMapCharts = echartsMapChartMapper.selectJInTianData();
        //从贸易表中获取
        List<EchartsMapChart> echartsMapCharts = echartsMapChartMapper.selectEcsTransactionAmount();
        for (EchartsMapChart e:echartsMapCharts){
            for(int i =0;i<currencys.length;i++){
                if (currencys[i].substring(0,3).equals(e.getCurrency())){
                    e.setCurrency(currencys[i].substring(3,currencys[i].length()));
                }
            }
            for(int j=0;j<customsCodes.length;j++){
                if(customsCodes[j].substring(0,4).equals(e.getName())){
                    e.setName("海关编码:"+e.getName());
                }
            }
            if (e.getCurrency().equals("人民币")){
                e.setName(e.getName()+"￥");
                e.setCurrency(null);
            }else if(e.getCurrency().equals("美元")){
                e.setName(e.getName()+"$");
                e.setCurrency(null);
            }else{
                e.setName(e.getName()+e.getCurrency());
                e.setCurrency(null);
            }
        }

        return echartsMapCharts;
    }


}
