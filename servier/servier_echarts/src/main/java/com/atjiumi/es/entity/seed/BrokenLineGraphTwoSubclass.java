package com.atjiumi.es.entity.seed;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="这是折现图子类对象", description="")
@AllArgsConstructor
@NoArgsConstructor
public class BrokenLineGraphTwoSubclass {

    private Integer name;
}
