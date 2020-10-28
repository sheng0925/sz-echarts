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
@ApiModel(value="导航栏", description="")
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)//如果对象中的属性没有值自动忽略掉
public class EchartsNavigation {
        //获取申报地海关代码
        private String name;
        //对应金额
        private String value ;
        //币制对应编码
        private String currency;
}
