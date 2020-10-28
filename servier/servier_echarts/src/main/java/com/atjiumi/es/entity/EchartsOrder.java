package com.atjiumi.es.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Lang;

/**
 * @author 盛镇林
 * @date 2020/8/20 - 16:06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="订单", description="")
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)//如果对象中的属性没有值自动忽略掉
public class EchartsOrder {
    private String name; //时间
    private String currency;//币制/换成人民币的汇率
    private String sumMoney;//总价值
    private String sumWeight;//总重量
    private String sumOrder;//订单数
}
