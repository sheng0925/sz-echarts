package com.atjiumi.es.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="这是折线图对象", description="")
@AllArgsConstructor
@NoArgsConstructor
public class EchartsBrokenLineGraph {
    //private String naem;
    //private List<List<EchartsBrokenLineGraph>> lists;

    private Integer Jan;//一月
    private Integer Feb;//二月
    private Integer Mar;//三月
    private Integer Apr;//四月
    private Integer May;//五月
    private Integer Jun;//六月
    private Integer Jul;//七月
    private Integer Aug;//八月
    private Integer Sep;//九月
    private Integer Octs;//十月
    private Integer Nov;//十一月
    private Integer Decs;//十二月

}
