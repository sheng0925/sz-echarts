package com.atjiumi.es.service.impl;

import com.atjiumi.es.Config.RemoteProperties;
import com.atjiumi.es.entity.EchartsMap;
import com.atjiumi.es.entity.EchartsNavigation;
import com.atjiumi.es.entity.EchartsOrder;
import com.atjiumi.es.entity.seed.orderTest;
import com.atjiumi.es.mapper.EchartsMapMapper;
import com.atjiumi.es.mapper.EchartsOrderMapper;
import com.atjiumi.es.service.EchartsMapService;
import com.atjiumi.es.service.EchartsOrderService;
import com.atjiumi.es.utils.R;
import com.atjiumi.es.utils.getDollarRatioUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.time.Month;
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
@Service
public class EchartsOrderServiceImpl extends ServiceImpl<EchartsOrderMapper, EchartsOrder> implements EchartsOrderService {

    //注入获取当前汇率对象
    @Autowired
    private getDollarRatioUtils  request1;
    //获取到配置文件中对应的字段编码
    @Autowired
    private  RemoteProperties remoteProperties;
    //注入mapper对象
    @Autowired
    private EchartsOrderMapper echartsOrderMapper;

    /**
     * 查询订单昨日，一个月，今年，去年的数据
     */
    public List<EchartsOrder> selectOrder(){
        List<EchartsOrder> echartsOrders = echartsOrderMapper.selectEchartsOrder();
        return echartsOrders;

    }

    //判断一个字符串是否带有小数点
    private boolean validateNumber(String str) {
        if(StringUtils.isBlank(str)) {
            return false;
        }
        //返回true表示没有小数点，返回false表示有小数点
        return str.matches("[+-]?[0-9]+(\\\\.[0-9]+)?");
    }

    @Override
    public EchartsOrder selectTestcurrency() {
        EchartsOrder order =new EchartsOrder();
        //获取当前人民币对美元汇率
       // Float d = Float.parseFloat(request1.getRequest1()) / 100;
        Float d =6.81F;
        int sum = 0;//美元转换人民币值
        int rsum = 0;//人民币
        int ssum;//总价值
        int weight;//重量
        String currency = remoteProperties.getCurrency();
        String[] split = currency.split(",");
        List<EchartsOrder> list = echartsOrderMapper.selectTestcurrency();

        int w =0;
        for (int j = 0; j < list.size(); j++) {
            for (int i = 0; i < split.length; i++) {
                if (split[i].substring(0, 3).equals(list.get(j).getCurrency())
                        && split[i].substring(3, split[i].length()).equals("美元")) {
                    //美元转成人民币
                    //判断是否有小数点，有就去掉小数点
                    if(validateNumber(list.get(j).getSumMoney())){
                        sum += (int) (Integer.parseInt(list.get(j).getSumMoney()) * d);
                    }else{
                        sum += (int) (Integer.parseInt(list.get(j).getSumMoney().substring(0,list.get(j).getSumMoney().indexOf("."))) * d);
                    }
                    w++;
                    break;
                }
            }
            //判断如果当前美元已经转换就跳过本次循环
            if(w>0){
                w=0;
                continue;
            }
            if(validateNumber(list.get(j).getSumMoney())){
                rsum += Integer.parseInt(list.get(j).getSumMoney());
            }else{
                rsum += Integer.parseInt(list.get(j).getSumMoney().substring(0,list.get(j).getSumMoney().indexOf(".")));
            }
        }
        ssum =(sum+rsum)/10000;
        int sumweight=0;//总重量
        Long sumorder=0L;//总订单
        //存入金额
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(Locale.CHINA);
        order.setSumMoney(currencyInstance.format(ssum));
        //存入日期
        order.setName("昨日订单");
        for(int i =0;i<list.size();i++){
            if(validateNumber(list.get(i).getSumWeight())){
                sumweight+=Integer.parseInt(list.get(i).getSumWeight())/1000;

            }else{
                sumweight+=Integer.parseInt(list.get(i).getSumWeight().substring(0,list.get(i).getSumWeight().indexOf(".")))/1000;
            }
            sumorder+=Long.parseLong(list.get(i).getSumOrder());

        }

        //存入总重量
        order.setSumWeight(Integer.toString(sumweight));
        //存入订单数量
        if(sumorder>10000){
            order.setSumOrder(Long.toString(sumorder/10000));
        }else {
            order.setSumOrder(Long.toString(sumorder)+"件");
        }

        return order;
    }

    @Override
    public EchartsOrder selectTestMonth() {
        EchartsOrder order =new EchartsOrder();
        //获取当前人民币对美元汇率
        //Float d = Float.parseFloat(request1.getRequest1()) / 100;
        Float d =6.81F;
        int sum = 0;//美元转换人民币值
        Integer rsum = 0;//人民币
        int ssum;//总价值
        int weight;//重量
        String currency = remoteProperties.getCurrency();
        String[] split = currency.split(",");
        List<EchartsOrder> list = echartsOrderMapper.selectTestMonth();
        int w =0;
        for (int j = 0; j < list.size(); j++) {
            for (int i = 0; i < split.length; i++) {
                if (split[i].substring(0, 3).equals(list.get(j).getCurrency())
                        && split[i].substring(3, split[i].length()).equals("美元")) {
                    //判断是否有小数点
                    //美元转成人民币
                    if(validateNumber(list.get(j).getSumMoney())){
                        sum += (int) (Integer.parseInt(list.get(j).getSumMoney()) * d);
                    }else{
                        sum += (int) (Integer.parseInt(list.get(j).getSumMoney().substring(0,list.get(j).getSumMoney().indexOf("."))) * d);
                    }
                    w++;
                    break ;
                }
            }
            if(w>0){
                w=0;
                continue;
            }
            if(validateNumber(list.get(j).getSumMoney())){
                rsum += Integer.parseInt(list.get(j).getSumMoney());
            }else {
                rsum += Integer.parseInt(list.get(j).getSumMoney().substring(0, list.get(j).getSumMoney().indexOf(".")));
            }

        }
        ssum =(sum+rsum)/10000;
        int sumweight=0;//总重量
        Long sumorder=0L;//总订单
        //存入金额
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(Locale.CHINA);
        order.setSumMoney(currencyInstance.format(ssum));
        //存入日期
        order.setName("本月订单");
        for(int i =0;i<list.size();i++){
            if (validateNumber(list.get(i).getSumWeight())){
                sumweight+=Integer.parseInt(list.get(i).getSumWeight())/1000;
            }else {
                sumweight+=Integer.parseInt(list.get(i).getSumWeight().substring(0,list.get(i).getSumWeight().indexOf(".")))/1000;
            }
            sumorder+=Long.parseLong(list.get(i).getSumOrder());
        }
        //存入总重量
        order.setSumWeight(Integer.toString(sumweight));
        //存入订单数量
        order.setSumOrder(Long.toString(sumorder/10000));
        return order;
    }

    @Override
    public EchartsOrder selectTestOneYear() {
        EchartsOrder order =new EchartsOrder();
        //获取当前人民币对美元汇率
        //Float d = Float.parseFloat(request1.getRequest1()) / 100;
        Float d =6.81F;
        long sum = 0;//美元转换人民币值
        long rsum = 0;//人民币
        long ssum;//总价值
        long weight;//重量
        String currency = remoteProperties.getCurrency();
        String[] split = currency.split(",");
        List<EchartsOrder> orderTests = echartsOrderMapper.selectTestOneYear();
        int w =0;
        for (int j = 0; j < orderTests.size(); j++) {
            for (int i = 0; i < split.length; i++) {
                if (split[i].substring(0, 3).equals(orderTests.get(j).getCurrency())
                        && split[i].substring(3, split[i].length()).equals("美元")) {
                    //判断是否有小数点
                    //美元转成人民币
                    if(validateNumber(orderTests.get(j).getSumMoney())){
                        sum += (long) (Long.parseLong(orderTests.get(j).getSumMoney()) * d);
                    }else{
                        sum += (long) (Long.parseLong(orderTests.get(j).getSumMoney().substring(0,orderTests.get(j).getSumMoney().indexOf("."))) * d);
                    }
                    w++;
                    break;
                }
            }
            if(w>0){
                w=0;
                continue;
            }
            if(validateNumber(orderTests.get(j).getSumMoney())){
                rsum += Long.parseLong(orderTests.get(j).getSumMoney());
            }else {
                rsum += Long.parseLong(orderTests.get(j).getSumMoney().substring(0,orderTests.get(j).getSumMoney().indexOf(".")));

            }

        }
        ssum =(sum+rsum)/10000;
        int sumweight=0;//总重量
        Long sumorder=0L;//总订单
        //存入金额
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(Locale.CHINA);
        order.setSumMoney(currencyInstance.format(ssum));
        //存入日期
        order.setName("今年订单");
        for(int i =0;i<orderTests.size();i++){
            if(validateNumber(orderTests.get(i).getSumWeight())){
                sumweight+=Integer.parseInt(orderTests.get(i).getSumWeight())/1000;
            }else{
                sumweight+=Integer.parseInt(orderTests.get(i).getSumWeight().substring(0,orderTests.get(i).getSumWeight().indexOf(".")))/1000;
            }
            sumorder+=Long.parseLong(orderTests.get(i).getSumOrder());
        }
        //存入总重量
        order.setSumWeight(Integer.toString(sumweight));
        //存入订单数量
        //减一是因为一个订单中多个订单且币制又有所不同，如果需要分组订单和币制分不出，而且这个数据也是不对的，并且只有这一个订单，所以就直接减一了
        order.setSumOrder(Long.toString((sumorder-1)/10000));

        return order;
    }

    @Override
    public EchartsOrder selectLastYearCurrency() {
        EchartsOrder order =new EchartsOrder();
        //获取当前人民币对美元汇率
        //Float d = Float.parseFloat(request1.getRequest1()) / 100;
        Float d =6.81F;
        long sum = 0;//美元转换人民币值
        long rsum = 0;//人民币
        long ssum;//总价值
        long weight;//重量
        String currency = remoteProperties.getCurrency();
        String[] split = currency.split(",");
        List<EchartsOrder> orderTests = echartsOrderMapper.selectLastYearCurrency();
        int w =0;
        for (int j = 0; j < orderTests.size(); j++) {
            for (int i = 0; i < split.length; i++) {
                if (split[i].substring(0, 3).equals(orderTests.get(j).getCurrency())
                        && split[i].substring(3, split[i].length()).equals("美元")) {
                    //美元转成人民币
                    if(validateNumber(orderTests.get(j).getSumMoney())){
                        sum += (long) (Long.parseLong(orderTests.get(j).getSumMoney()) * d);
                    }else {
                        sum += (long) (Long.parseLong(orderTests.get(j).getSumMoney().substring(0, orderTests.get(j).getSumMoney().indexOf("."))) * d);
                    }
                    w++;
                    break ;
                }
            }
            if(w>0){
                w=0;
                continue;
            }
            if(validateNumber(orderTests.get(j).getSumMoney())){
                rsum += Long.parseLong(orderTests.get(j).getSumMoney());
            }else {
                rsum += Long.parseLong(orderTests.get(j).getSumMoney().substring(0,orderTests.get(j).getSumMoney().indexOf(".")));
            }

        }

        ssum =(sum+rsum)/10000;

        int sumweight=0;//总重量
        Long sumorder=0L;//总订单

        //存入金额
        //将stirng格式转成货币格式
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(Locale.CHINA);
        order.setSumMoney(currencyInstance.format(ssum));
        //存入日期
        order.setName("去年订单");
        for(int i =0;i<orderTests.size();i++){
            if (validateNumber(orderTests.get(i).getSumWeight())){
                sumweight+=Integer.parseInt(orderTests.get(i).getSumWeight())/1000;
            }else{
                sumweight+=Integer.parseInt(orderTests.get(i).getSumWeight().substring(0,orderTests.get(i).getSumWeight().indexOf(".")))/1000;
            }

            sumorder+=Long.parseLong(orderTests.get(i).getSumOrder());
        }
        //存入总重量
        order.setSumWeight(Integer.toString(sumweight));
        //存入订单数量
        order.setSumOrder(Long.toString(sumorder/10000));

        return order;
    }


}
