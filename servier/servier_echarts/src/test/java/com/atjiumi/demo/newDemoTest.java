package com.atjiumi.demo;

import com.atjiumi.es.Config.RemoteProperties;
import com.atjiumi.es.EchartsApplication;
import com.atjiumi.es.entity.EchartsMapChart;
import com.atjiumi.es.entity.EchartsOrder;
import com.atjiumi.es.entity.seed.orderTest;
import com.atjiumi.es.mapper.EchartsMapChartMapper;
import com.atjiumi.es.mapper.EchartsOrderMapper;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.atjiumi.demo.JuheDemo.net;

/**
 * @author 盛镇林
 * @date 2020/9/20 - 16:24
 * 用于测试新表的方法
 */
@EnableConfigurationProperties(RemoteProperties.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EchartsApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class newDemoTest {
    @Autowired
    EchartsMapChartMapper echartsMapChartMapper;

    @Autowired
    RemoteProperties remoteProperties;

    @Autowired
    EchartsOrderMapper echartsOrderMapper;

    @Test
    public void testMapChart() {
        List<EchartsMapChart> list = new ArrayList<>();
        EchartsMapChart e = new EchartsMapChart();
        EchartsMapChart e1 = new EchartsMapChart();
        e.setCurrency("142");
        e1.setCurrency("502");
        e.setName("5314");
        e1.setName("5349");
        e.setValue(23234);
        e1.setValue(234234);
        list.add(e);
        list.add(e1);

        echartsMapChartMapper.INSERTMapChartTodayTop(list);
        List<EchartsMapChart> list1 = echartsMapChartMapper.selectEcsTransactionAmount();
        for (EchartsMapChart e2 : list1) {
            System.out.println(e2);
        }
        //echartsMapChartMapper.deleteMapChartAll();
    }

    /**
     * 将美元和人民币区分开
     */
    String request1 = JuheDemo.getRequest1();
    @Test
    public void selectTestcurrency() {
        orderTest order =new orderTest();
        //获取当前人民币对美元汇率
        Float d = Float.parseFloat(request1) / 100;
        int sum = 0;//美元转换人民币值
        int rsum = 0;//人民币
        int ssum;//总价值
        int weight;//重量
        String currency = remoteProperties.getCurrency();
        String[] split = currency.split(",");
        List<EchartsOrder> list = echartsOrderMapper.selectTestcurrency();
        a:for (int j = 0; j < list.size(); j++) {
            for (int i = 0; i < split.length; i++) {
                if (split[i].substring(0, 3).equals(list.get(j).getCurrency())
                        && split[i].substring(3, split[i].length()).equals("美元")) {
                    //美元转成人民币
                    sum += (int) (Integer.parseInt(list.get(j).getSumMoney()) * d);
                    //将1kg转成吨
                     weight= Integer.parseInt(list.get(i).getSumWeight())/1000;
                     //在存入到对象中
                    list.get(i).setSumWeight(Integer.toString(weight));
                    break a;
                }
            }
            rsum += Integer.parseInt(list.get(j).getSumMoney());
            //将1kg转成吨
            weight= Integer.parseInt(list.get(j).getSumWeight())/1000;
            //在存入到对象中
            list.get(j).setSumWeight(Integer.toString(weight));
        }
        ssum =(sum+rsum)/10000;
        int sumweight=0;//总重量
        Long sumorder=0L;//总订单
        //存入金额
        order.setSumMoney(Integer.toString(ssum));
        //存入日期
        order.setName("昨日订单");
        for(int i =0;i<list.size();i++){
            sumweight+=Integer.parseInt(list.get(i).getSumWeight());
            sumorder+=Long.parseLong(list.get(i).getSumOrder());
        }
        //存入总重量
        order.setSumWeight(Integer.toString(sumweight));
        //存入订单数量
        order.setSumOrder(sumorder/10000);
        System.out.println(order);
    }

    /**
     * 去年订单
     */
    @Test
    public void  selectLastYearCurrency(){
        orderTest order =new orderTest();
        //获取当前人民币对美元汇率
        //Float d = Float.parseFloat(request1) / 100;
        Float d = 6.898F;
        long sum = 0;//美元转换人民币值
        long rsum = 0;//人民币
        long ssum;//总价值
        long weight;//重量
        String currency = remoteProperties.getCurrency();
        String[] split = currency.split(",");
        List<EchartsOrder> orderTests = echartsOrderMapper.selectLastYearCurrency();
        System.out.println("集合对象："+orderTests);
        int w =0;
        for (int j = 0; j < orderTests.size(); j++) {
             for (int i = 0; i < split.length; i++) {
                if (split[i].substring(0, 3).equals(orderTests.get(j).getCurrency())
                        && split[i].substring(3, split[i].length()).equals("美元")) {
                    //美元转成人民币
                    sum += (long) (Long.parseLong(orderTests.get(j).getSumMoney()) * d);

                    w++;
                    break;
                }
            }
            if(w>0){
                w=0;
                continue;
            }
            rsum = rsum+Long.parseLong(orderTests.get(j).getSumMoney());
        }
        ssum =(sum+rsum)/10000;

        int sumweight=0;//总重量
        Long sumorder=0L;//总订单
        //存入金额
        order.setSumMoney(Long.toString(ssum));
        //存入日期
        order.setName("去年订单");
        for(int i =0;i<orderTests.size();i++){
            sumweight+=Integer.parseInt(orderTests.get(i).getSumWeight())/1000;
            sumorder+=Long.parseLong(orderTests.get(i).getSumOrder());

        }
        //存入总重量
        order.setSumWeight(Integer.toString(sumweight));
        //存入订单数量
        order.setSumOrder(sumorder-1);
        System.out.println(order);
    }

    /**
     * 今年订单
     */
    @Test
    public void selectTestOneYear(){
        orderTest order =new orderTest();
        //获取当前人民币对美元汇率
        Float d = Float.parseFloat(request1) / 100;
        long sum = 0;//美元转换人民币值
        long rsum = 0;//人民币
        long ssum;//总价值
        long weight;//重量
        String currency = remoteProperties.getCurrency();
        String[] split = currency.split(",");
        List<EchartsOrder> orderTests = echartsOrderMapper.selectTestOneYear();
        a:for (int j = 0; j < orderTests.size(); j++) {
            for (int i = 0; i < split.length; i++) {
                if (split[i].substring(0, 3).equals(orderTests.get(j).getCurrency())
                        && split[i].substring(3, split[i].length()).equals("美元")) {
                    //美元转成人民币
                    sum += (long) (Long.parseLong(orderTests.get(j).getSumMoney()) * d);
                    //将1kg转成吨
                    weight= Long.parseLong(orderTests.get(i).getSumWeight())/1000;
                    //在存入到对象中
                    orderTests.get(i).setSumWeight(Long.toString(weight));
                    break a;
                }
            }
            rsum += Long.parseLong(orderTests.get(j).getSumMoney());
            //将1kg转成吨
            weight= Long.parseLong(orderTests.get(j).getSumWeight())/1000;
            //在存入到对象中
            orderTests.get(j).setSumWeight(Long.toString(weight));
        }
        ssum =(sum+rsum)/10000;
        int sumweight=0;//总重量
        Long sumorder=0L;//总订单
        //存入金额
        order.setSumMoney(Long.toString(ssum));
        //存入日期
        order.setName("去年订单");
        for(int i =0;i<orderTests.size();i++){
            sumweight+=Integer.parseInt(orderTests.get(i).getSumWeight());
            sumorder+=Long.parseLong(orderTests.get(i).getSumOrder());
        }
        //存入总重量
        order.setSumWeight(Integer.toString(sumweight));
        //存入订单数量W
        order.setSumOrder(sumorder-1);
        System.out.println(order);
    }

    /**
     * 本月
     */
    @Test
    public void selectTestMonth(){
        orderTest order =new orderTest();
        //获取当前人民币对美元汇率
        Float d = Float.parseFloat(request1) / 100;
        int sum = 0;//美元转换人民币值
        int rsum = 0;//人民币
        int ssum;//总价值
        int weight;//重量
        String currency = remoteProperties.getCurrency();
        String[] split = currency.split(",");
        List<EchartsOrder> list = echartsOrderMapper.selectTestMonth();
        a:for (int j = 0; j < list.size(); j++) {
            for (int i = 0; i < split.length; i++) {
                if (split[i].substring(0, 3).equals(list.get(j).getCurrency())
                        && split[i].substring(3, split[i].length()).equals("美元")) {
                    //美元转成人民币
                    sum += (int) (Integer.parseInt(list.get(j).getSumMoney()) * d);
                    //将1kg转成吨
                    weight= Integer.parseInt(list.get(i).getSumWeight())/1000;
                    //在存入到对象中
                    list.get(i).setSumWeight(Integer.toString(weight));
                    break a;
                }
            }
            rsum += Integer.parseInt(list.get(j).getSumMoney());
            //将1kg转成吨
            weight= Integer.parseInt(list.get(j).getSumWeight())/1000;
            //在存入到对象中
            list.get(j).setSumWeight(Integer.toString(weight));
        }
        ssum =(sum+rsum)/10000;
        int sumweight=0;//总重量
        Long sumorder=0L;//总订单
        //存入金额
        order.setSumMoney(Integer.toString(ssum));
        //存入日期
        order.setName("本月订单");
        for(int i =0;i<list.size();i++){
            sumweight+=Integer.parseInt(list.get(i).getSumWeight());
            sumorder+=Long.parseLong(list.get(i).getSumOrder());
        }
        //存入总重量
        order.setSumWeight(Integer.toString(sumweight));
        //存入订单数量
        order.setSumOrder(sumorder-1);
        System.out.println(order);
    }




}
