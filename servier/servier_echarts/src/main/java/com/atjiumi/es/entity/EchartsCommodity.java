package com.atjiumi.es.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 盛镇林
 * @date 2020/9/18 - 15:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="热销商品", description="")
@AllArgsConstructor
@NoArgsConstructor
public class EchartsCommodity {
    //热销商品
    String name;
    //名称出现的次数
    Integer value;
}
