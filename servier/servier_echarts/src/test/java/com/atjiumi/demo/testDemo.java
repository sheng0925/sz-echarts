package com.atjiumi.demo;

import com.atjiumi.es.Config.RemoteProperties;
import com.atjiumi.es.EchartsApplication;
import com.atjiumi.es.controller.EhcartsMapcontroller;
import com.atjiumi.es.entity.*;
import com.atjiumi.es.entity.seed.BrokenLineGraphTwoSubclass;
import com.atjiumi.es.entity.seed.orderTest;
import com.atjiumi.es.mapper.*;
import com.atjiumi.es.utils.QuChongFuUtils;
import org.apache.ibatis.annotations.Lang;
import org.apache.poi.hssf.record.TabIdRecord;
import org.apache.velocity.runtime.directive.Foreach;
import org.apache.velocity.runtime.directive.contrib.For;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.script.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.NumberFormat;
import java.util.*;

import org.slf4j.Logger;
//import java.util.logging.Logger;

/**
 * @author 盛镇林
 * @date 2020/9/9 - 10:48
 */
@EnableConfigurationProperties(RemoteProperties.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EchartsApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class testDemo {


    @Autowired
    EchartsTestMapper echartsTestMapper;


    @Autowired
    EchartsOrderMapper echartsOrderMapper;

    @Autowired
    EchartsPieMapper echartsPieMapper;

    @Autowired
    EchartsMapMapper echartsMapMapper;

    @Autowired
    EchartsNavigationMapper echartsNavigationMapper;

    @Autowired
    EchartsMapChartMapper echartsMapChartMapper;

    @Autowired
    EchartsBrokenLineGraphTwoMapper echartsBrokenLineGraphTwoMapper;

    @Autowired
    EchartsCommodityMapper echartsCommodityMapper;

    @Autowired
    RemoteProperties remoteProperties;

    /**
     * 测试获取历史深圳机场快件，前海保税的历史出口金额
     */
    String request1 = JuheDemo.getRequest1();
    @Test
    public void testselectJInTianData(){
        orderTest order =new orderTest();
        //获取当前人民币对美元汇率
        Float d = Float.parseFloat(request1) / 100;
        System.out.println("美元汇率"+d);
        long sum = 0;//美元转换人民币值
        //币制编码
        String currency = remoteProperties.getCurrency();
        String[] split = currency.split(",");
        List<EchartsNavigation> echartsNavigations = echartsNavigationMapper.selectHistoryData();
        //转换美元
        for (int j = 0; j < echartsNavigations.size(); j++) {
            for (int i = 0; i < split.length; i++) {
                if (split[i].substring(0, 3).equals(echartsNavigations.get(j).getCurrency())
                        && split[i].substring(3, split[i].length()).equals("美元")) {
                    //美元转成人民币
                    sum +=  (Long.parseLong(echartsNavigations.get(j).getValue()) * d);
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
                l +=Long.parseLong(echartsNavigations.get(i).getValue());
            }else{
                l1+=Long.parseLong(echartsNavigations.get(i).getValue());
            }
        }
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(Locale.CHINA);
        String szjckj = currencyInstance.format(l);
        String qhbs = currencyInstance.format(l1);
        List<EchartsNavigation> list =new ArrayList<>();
        list.add(new EchartsNavigation("深圳机场快件",szjckj,null));
        list.add(new EchartsNavigation("前海保税",qhbs,null));

    }

    @Test
    public void  testPIE(){
        List<EchartsPie> list =new ArrayList<>();
        EchartsPie e =new EchartsPie();
        e.setName("2");
        e.setValue(34);
        EchartsPie e2 =new EchartsPie();
        e2.setName("4");
        e2.setValue(64);
        list.add(e);
        list.add(e2);
         echartsPieMapper.INSERTPieTodayTop(list);
        List<EchartsPie> echartsPies = echartsPieMapper.selectTodayTraffic();
        for (EchartsPie ec:echartsPies){
            System.out.println(ec);
        }
    }


    @Test
    public void testCommodity(){
        QuChongFuUtils qcfu =new QuChongFuUtils();
        List<EchartsCommodity> echartsCommodities = echartsCommodityMapper.selecCommodityTopFive();
        String str ="";
        for (EchartsCommodity e :echartsCommodities){
            str+=e.getName();
        }
        String[] split = str.split(",");
        List array = qcfu.array(split, new ArrayList());
        for (int i =0;i<5;i++){
            System.out.println(array.get(i));
        }
    }


    @Test
    public  void testBrokenlineGraphTWo(){
        //一个集合存放本月和上个月数据集合
        List<List<Integer>> lists =new ArrayList<>();
        List<EchartsBrokenLineGraphTwo> Twos = echartsBrokenLineGraphTwoMapper.selectBrokenLineTwo();
        List<EchartsBrokenLineGraphTwo> Twos1 = echartsBrokenLineGraphTwoMapper.selectLastMonth();


    }

    @Test
    public void testMapChart(){
        //币制对应编码
        String currency ="142人民币,502美元,116日本元,132新加坡元";
        //申报地对应编码
        String customsCode ="5314深关邮办,5349前海港区,4908长沙黄花机场海关,4713武汉海关快件监管中心,4019南昌综保,5316大鹏海关,7220友谊关,4025南昌邮件";
        String[] currencys = currency.split(",");
        String[] customsCodes = customsCode.split(",");
        List<EchartsMapChart> echartsMapCharts = echartsMapChartMapper.selectJInTianData();
        for (EchartsMapChart e:echartsMapCharts){
          for(int i =0;i<currencys.length;i++){
              if (currencys[i].substring(0,3).equals(e.getCurrency())){
                  e.setCurrency(currencys[i].substring(3,currencys[i].length()));
              }
          }
          for(int j=0;j<customsCodes.length;j++){
                if(customsCodes[j].substring(0,4).equals(e.getName())){
                    e.setName(customsCodes[j].substring(4,customsCodes[j].length()));
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
         System.out.println(e);
        }

    }

    @Test
    public void testnavigation(){
        String str ="5314深关邮办,5349前海港区,4908长沙黄花机场海关,4713武汉海关快件监管中心,4019南昌综保,5316大鹏海关,7220友谊关,4025南昌邮件";
        String[] split = str.split(",");
        List<EchartsNavigation> echartsNavigations = echartsNavigationMapper.selectHistoryData();
        for(EchartsNavigation navigation:echartsNavigations){
            for(int i =0;i<split.length;i++){
                if(navigation.getName().equals(split[i].substring(0,4))){
                    navigation.setName(split[i].substring(4,split[i].length()));
                    break;
                }
            }
        }


    }

    @Test
    public void testmap(){
        /*获取执行JavaScript的执行引擎*/
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
        /*为文件注入全局变量*/
        Bindings bindings = engine.createBindings();
        /*设置绑定参数的作用域*/
        engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
        // 获得js文件
        try {
            engine.eval(new FileReader("D:/work/vscode-workspace02/deno/js"+"/cus-constant.js"));
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Object obj= bindings.get("street");
        String str =(String) obj;
        //目的国编码对应的国家数组
        String[] split = str.split(",");
        int k =0;
        List<EchartsMap> echartsMaps = echartsMapMapper.selectTodayPod();
        for(int j =0;j<echartsMaps.size();j++){
            for(int i =0;i<split.length;i++){
                k++;
                if (split[i].substring(0,3).equals(echartsMaps.get(j).getName())){
                    echartsMaps.get(j).setName(split[i].substring(3,split[i].length()));
                    echartsMaps.get(j).setDestination("深圳");
                }
            }
        }
        System.out.println(k);

    }


    @Test
    public void  testPie(){
        List<EchartsPie> echartsPies = echartsPieMapper.selectPieTopThree();
        String str ="0非保税区,1监管仓库,2水路运输,3铁路运输,4公路运输,5航空运输,6邮件运输,7保税区,8保税仓库," +
                "9其他运输方式,A全部运输方式,H边境特殊海关作业区,T综合试验区,W物料中心,X物流园区,Y保税港区,Z出口加工区";
        String[] split = str.split(",");
        int j =0;
        wc:for(EchartsPie e :echartsPies){
            for (int i =0;i<split.length;i++){
                j++;
                if (split[i].substring(0,1).equals(e.getName())){
                        e.setName(split[i].substring(1,split[i].length()));
                       break wc;
                }
            }
        }
        System.out.println(j);

    }




    @Test
    public void  selectJS(){
        /*获取执行JavaScript的执行引擎*/
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
        /*为文件注入全局变量*/
        Bindings bindings = engine.createBindings();
        /*设置绑定参数的作用域*/
        engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
        // 获得js文件
        try {
            engine.eval(new FileReader("D:/work/vscode-workspace02/deno/js"+"/cus-constant.js"));
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Object obj= bindings.get("street");
        String str =(String) obj;
        //目的国编码对应的国家数组
        String[] split = str.split(",");
        //目的地货品数量排行前八
        List<EchartsTest> echartsTests = echartsTestMapper.selectAll();
        for(int j =0;j<echartsTests.size();j++){
            for(int i =0;i<split.length;i++){
               if (split[i].substring(0,3).equals(echartsTests.get(j).getName())){
                   echartsTests.get(j).setName(split[i].substring(3,split[i].length()));
                   echartsTests.get(j).setValue(echartsTests.get(j).getValue()/10000);
               }
            }
        }
        //Map<Object,Object> map = (Map) bindings.get("street");\r\n
    }

    @Autowired
    TimerManager timerManager;

    @Test
    public void testApplication(){
        timerManager.start();
    }

    //初始化类信息，答应日志时候就能知道是在那个类打印的
    protected static final Logger logger = LoggerFactory.getLogger(testDemo.class);

    @Test
    public void attat(){
        logger.info("打印日志");

    }
}
