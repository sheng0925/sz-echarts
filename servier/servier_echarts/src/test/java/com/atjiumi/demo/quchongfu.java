package com.atjiumi.demo;

import com.atjiumi.es.entity.EchartsCommodity;
import org.junit.Test;

import java.util.*;

/**
 * @author 盛镇林
 *
 * @date 2020/9/18 - 1:19
 */
public class quchongfu {

    @Test
    public void aa(){
        String str ="塑胶壳,笔,塑胶保护套,挂绳,适配器,塑胶壳,五金配件,衣服,数据线,有线耳机,塑胶保护壳,五金配件,衣服,数据线,有线耳机,塑胶壳,五金配件,数据线,衣服,有线耳机," +
                "塑胶保护壳,五金配件,数据线,衣服,有线耳机,塑胶壳,塑胶保护套,笔,挂绳,适配器,五金配件,塑料保护壳,衣服,数据线,有线耳机,塑胶壳,笔,塑胶保护套,适配器,挂绳," +
                "衣服,公仔,饰品,地垫,摆件,衣服,塑胶壳,笔,塑胶保护套,挂绳";
        String sb []= str.split(",");
        List<EchartsCommodity> listCommodity =new ArrayList<>();
        List array = array(sb,listCommodity);
        for(int i =0;i<array.size();i++){
            System.out.println(array.get(i));
        }

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, (a, b) -> b.compareTo(a));
        System.out.println(names);
    }

    //将字符去重复，并记录其中的数量
    public static List array(String[] array,List arrlist){
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            Integer integer = map.get(array[i]);
            map.put(array[i], integer == null?1:integer+1);
        }


        //注意 ArrayList<>() 括号里要传入map.entrySet()
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
        {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {
                //按照value值，重小到大排序
//                return o1.getValue() - o2.getValue();
                //按照value值，从大到小排序
                return o2.getValue() - o1.getValue();
                //按照value值，用compareTo()方法默认是从小到大排序
//                return o1.getValue().compareTo(o2.getValue());
            }
        });
        //注意这里遍历的是list，也就是我们将map.Entry放进了list，排序后的集合
        for (Map.Entry s : list)
        {
            //System.out.println(s.getKey()+"--"+s.getValue());
            EchartsCommodity es =new EchartsCommodity();
            es.setName((String) s.getKey());
            es.setValue((Integer) s.getValue());
            arrlist.add(es);
        }

        //将排序的数据返回
        return  arrlist;
    }
}
