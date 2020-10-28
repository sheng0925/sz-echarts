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
@ApiModel(value="交通", description="")
@AllArgsConstructor
@NoArgsConstructor
public class EchartsPie {

    private String name;

    private Integer value;
}
