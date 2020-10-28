package com.atjiumi.es.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="中间四个圆", description="")
@AllArgsConstructor
@NoArgsConstructor
public class EchartsCircle {


    private Integer CircleOne;
    private Integer CircleTwo;
    private Integer CircleThree;
    private Integer CircleFour;
}
