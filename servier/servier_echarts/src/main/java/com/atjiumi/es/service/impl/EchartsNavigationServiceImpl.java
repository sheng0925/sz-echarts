package com.atjiumi.es.service.impl;

import com.atjiumi.es.Config.RemoteProperties;
import com.atjiumi.es.entity.EchartsMap;
import com.atjiumi.es.entity.EchartsNavigation;
import com.atjiumi.es.entity.seed.orderTest;
import com.atjiumi.es.mapper.EchartsMapMapper;
import com.atjiumi.es.mapper.EchartsNavigationMapper;
import com.atjiumi.es.service.EchartsMapService;
import com.atjiumi.es.service.EchartsNavigationService;
import com.atjiumi.es.utils.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
public class EchartsNavigationServiceImpl extends ServiceImpl<EchartsNavigationMapper, EchartsNavigation> implements EchartsNavigationService {

    @Autowired
    private EchartsNavigationService echartsNavigationService;

    @Autowired
    RemoteProperties remoteProperties;

    @Autowired
    private EchartsNavigationMapper echartsNavigationMapper;

    @Override
    public R findAllEchartsNavigation() {
        return R.ok().data("itmes",echartsNavigationService.list(null));
    }


    //判断一个字符串是否带有小数点
    private boolean validateNumber(String str) {
            if(StringUtils.isBlank(str)) {
            return false;
            }
            // 说明一下的是该正则只能识别4位小数；如果不限制小数位数的话，写成[+-]?[0-9]+(\\.[0-9]+)?就可以了
            return str.matches("[+-]?[0-9]+(\\\\.[0-9]+)?");
}
    @Override
    public List<EchartsNavigation> selectHistoryData() {

        orderTest order =new orderTest();
        //获取当前人民币对美元汇率
        //Float d = Float.parseFloat(request1) / 100;
        Float d =6.79F;
        long sum = 0l;//美元转换人民币值
        //币制编码
        String currency = remoteProperties.getCurrency();
        String[] split = currency.split(",");
        //List<EchartsNavigation> echartsNavigations = echartsNavigationMapper.selectEchartsEchartsNavigation();
        List<EchartsNavigation> echartsNavigations = echartsNavigationMapper.selectHistoryData();

        //转换美元
        for (int j = 0; j < echartsNavigations.size(); j++) {
            for (int i = 0; i < split.length; i++) {
                if (split[i].substring(0, 3).equals(echartsNavigations.get(j).getCurrency())
                        & split[i].substring(3, split[i].length()).equals("美元")) {
                    //美元转成人民币
                    sum +=  (Long.parseLong(echartsNavigations.get(j).getValue().substring(0,echartsNavigations.get(j).getValue().indexOf("."))) * d);
                    //存入集合中
                    echartsNavigations.get(j).setValue(Long.toString(sum));
                    sum=0;
                    break ;
                }
            }
        }




        long l =0L;
        long l1 =0L;

        //转换申报地海关
        for(int i =0;i<echartsNavigations.size();i++){
            if(echartsNavigations.get(i).getName().equals("5314")){

                if(validateNumber(echartsNavigations.get(i).getValue())){
                    l+=Long.parseLong(echartsNavigations.get(i).getValue());
                }else {
                    l += Long.parseLong(echartsNavigations.get(i).getValue().substring(0, echartsNavigations.get(i).getValue().indexOf(".")));
                }
            }else {
                if (validateNumber(echartsNavigations.get(i).getValue())) {
                    l1 += Long.parseLong(echartsNavigations.get(i).getValue());
                } else {
                l1 += Long.parseLong(echartsNavigations.get(i).getValue().substring(0, echartsNavigations.get(i).getValue().indexOf(".")));
                }
            }
        }
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(Locale.CHINA);
        String szjckj = currencyInstance.format(l);
        String qhbs = currencyInstance.format(l1);
        List<EchartsNavigation> list =new ArrayList<>();
        list.add(new EchartsNavigation("5314",szjckj,null));
        list.add(new EchartsNavigation("5349",qhbs,null));
        System.out.println(list);
        return list;
    }

    @Override
    public List<EchartsNavigation> selectEchartsHistoryData() {
        return echartsNavigationMapper.selectEchartsEchartsNavigation();
    }
}
