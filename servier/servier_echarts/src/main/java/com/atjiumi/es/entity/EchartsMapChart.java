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
@ApiModel(value="今日贸易额", description="")
@AllArgsConstructor //有参构造器
@NoArgsConstructor //无参构造器
@JsonInclude(JsonInclude.Include.NON_NULL)//如果对象中的属性没有值自动忽略掉
public class EchartsMapChart {

    /**
     * 申报地海关代码
     */
    private String name;

    /**
     * 币制
     */
    private String currency;

    /**
     * 今日盈利金额
     */
    private Integer value;
}
