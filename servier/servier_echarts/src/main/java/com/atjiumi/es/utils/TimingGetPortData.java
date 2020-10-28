package com.atjiumi.es.utils;

import com.alibaba.fastjson.JSONObject;
import com.atjiumi.es.Config.InterfaceParameters;
import com.atjiumi.es.Config.RemoteProperties;
import com.atjiumi.es.entity.BcoDeclarationStatistics;
import com.atjiumi.es.mapper.BcoDeclarationStatisticsMapper;
import net.sf.json.JSONArray;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@Component
public class TimingGetPortData {

    @Autowired
    TimingUpDateData timingUpDateData;

    //@Scheduled(cron = "0 0 1 * * ?")//每天凌晨一点执行
    //@Scheduled(cron ="*/5 * * * * *")
    @Scheduled(fixedRate = 1000*60*60*24)
    public void taskRun(){
        jsondata();
        timingUpDateData.UpdateAll();

    }

    //获取到配置文件中对应的字段编码
    @Autowired
    private InterfaceParameters interfaceParameters;

    @Autowired
    BcoDeclarationStatisticsMapper bcoDeclarationStatisticsMapper;

    public  void jsondata() {
        Map<String ,String> map =new HashMap<>();
        map.put("Content-type", "application/json");
        map.put(interfaceParameters.getTokenName(),interfaceParameters.getTokenValue());
        String url =interfaceParameters.getUrl();
        JSONObject body=new JSONObject();
        body.put(interfaceParameters.getTokenName(), interfaceParameters.getTokenValue());
        String s = httpPostWithJsonAndHeader(url,body.toJSONString(),map);
        //转成json
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(s);
        //获取里面数组的内容
        JSONArray result = jsonObject.getJSONArray("result");
        net.sf.json.JSONObject row =null;

        //定义集合将对象存入集合中
        List<BcoDeclarationStatistics> bdslist =new ArrayList<>();

        //遍历数组，将值全部拿出
        for (int i =0;i<result.size();i++){
            row = result.getJSONObject(i);
            BcoDeclarationStatistics bds =new BcoDeclarationStatistics();
            bds.setCreateTime((String) row.get("createTime"));
            bds.setUpdateTime((String) row.get("updateTime"));
            bds.setId((String) row.get("id"));
            bds.setEbcCode((String) row.get("ebcCode"));
            bds.setEbcName((String) row.get("ebcName"));
            bds.setLogisticsCode((String) row.get("logisticsCode"));
            bds.setLogisticsName((String) row.get("logisticsName"));
            bds.setStatisticsFlag((String) row.get("statisticsFlag"));
            bds.setCountry((String) row.get("country"));
            bds.setPod((String) row.get("pod"));
            bds.setBillNo((String) row.get("billNo"));
            bds.setLogisticsTotal((Integer) row.get("logisticsTotal"));
            bds.setTotalPackNo((Integer) row.get("totalPackNo"));
            bds.setTotalGoodsValue(getBigDecimal(row.get("totalGoodsValue")));
            bds.setTotalGrossWeight(getBigDecimal(row.get("totalGrossWeight")));
            bds.setCustomsCode((String) row.get("customsCode"));
            bds.setCurrency((String) row.get("currency"));
            bds.setPortCode((String) row.get("portCode"));
            bds.setIeFlag((String) row.get("ieFlag"));
            bds.setIeDate((String) row.get("ieDate"));
            bds.setTrafMode((String) row.get("trafMode"));
            bds.setDomesticTrafNo((String) row.get("domesticTrafNo"));
            bds.setGoodsInfo((String) row.get("goodsInfo"));
            bds.setVoyageNo((String) row.get("voyageNo"));
            bds.setStatus((Integer) row.get("status"));
            bdslist.add(bds);
        }
//        for (BcoDeclarationStatistics b :bdslist){
//            System.out.println(b);
//        }
        bcoDeclarationStatisticsMapper.DeleteNine();
        bcoDeclarationStatisticsMapper.InsertBcoDeclarationStatistics(bdslist);

    }


    //将object类型转换成BigDecimal
    public  BigDecimal getBigDecimal(Object value ) {
        BigDecimal ret = null;
        if( value != null ) {
            if( value instanceof BigDecimal ) {
                ret = (BigDecimal) value;
            } else if( value instanceof String ) {
                ret = new BigDecimal( (String) value );
            } else if( value instanceof BigInteger) {
                ret = new BigDecimal( (BigInteger) value );
            } else if( value instanceof Number ) {
                ret = new BigDecimal( ((Number)value).doubleValue() );
            } else {
                throw new ClassCastException("Not possible to coerce ["+value+"] from class "+value.getClass()+" into a BigDecimal.");
            }
        }
        return ret;
    }


    public String httpPostWithJsonAndHeader(String url, String json, Map<String, String> headsMap) {
        String result = "";
        System.out.println("本次请求地址:{} "+url);
        System.out.println("本次传递数据:{} "+json);
        HttpPost httpPost = new HttpPost(url);
        System.out.println("是null吗+"+httpPost);
        StringEntity entity = new StringEntity(json, "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        //头
        if (headsMap != null && !headsMap.isEmpty()) {
            headsMap.forEach((key, value) -> {
                httpPost.addHeader(key, value);
            });
        }
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpPost)) {

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                System.out.println("HTTP请求成功");

                // 从响应模型中获取响应实体
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    result = EntityUtils.toString(responseEntity);
                }
            } else {
                System.out.println("HTTP请求失败");
                return null;
            }
            System.out.println("返回的结果"+result);
            return result;
        } catch (Exception e) {
            System.out.println("http请求出现异常"+e);
            return null;
        }
    }





}
