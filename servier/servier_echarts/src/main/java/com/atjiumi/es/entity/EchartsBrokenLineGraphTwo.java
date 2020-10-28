package com.atjiumi.es.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="这是折线图Tow对象", description="")
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)//如果对象中的属性没有值自动忽略掉
public class EchartsBrokenLineGraphTwo {


    /**
     * name 是日期（月份）
     * value 是月份的车次
     */
    private String name;
    private Integer value;
    //标记是本月还是上个月
//    private String name;
//    //一天
//    private Integer oneDay;
//    //二天
//    private Integer twoDays;
//    //三天
//    private Integer threeDays;
//    //四天
//    private Integer fourDays;
//    //五天
//    private Integer fiveDays;
//    //六天
//    private Integer sixDays;
//    //七天
//    private Integer sevenDays;
//    //八天
//    private Integer eightDays;
//    //九天
//    private Integer nineDays;
//    //十天
//    private Integer tenDays;
//    //十一天
//    private Integer elevenDays;
//    //十二天
//    private Integer twelveDays;
//    //十三天
//    private Integer thirteenDays;
//    //十四天
//    private Integer fourteenDays;
//    //十五天
//    private Integer fifteenDays;

}
