package com.atjiumi.es.utils;

import com.atjiumi.es.entity.EchartsCommodity;

import java.util.*;

/**
 *将String数组去重复，并排行，传入需要排序的集合，和返回的集合
 * @author 盛镇林
 * @date 2020/9/18 - 16:25
 */
public class QuChongFuUtils {

    //将字符去重复，并记录其中的数量
    public static List array(String[] array, List arrlist){
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
