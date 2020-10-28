package com.atjiumi.es.entity.seed;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 盛镇林
 * @date 2020/9/21 - 14:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="测试order币制对象", description="")
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)//如果对象中的属性没有值自动忽略掉
public class orderTest {
    private String name; //时间
    private String sumMoney;//总价值
    private String sumWeight;//总重量
    private Long sumOrder;//订单数
}
