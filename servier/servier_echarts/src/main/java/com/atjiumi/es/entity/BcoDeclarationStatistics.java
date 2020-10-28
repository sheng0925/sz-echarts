package com.atjiumi.es.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.sql.Timestamp;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="总记录表", description="")
@AllArgsConstructor
@NoArgsConstructor
public class BcoDeclarationStatistics {
        private String id; //ID
        private String ebcCode;//电商企业的海关注册登记编号或统一社会信用代码,对应清单的收发货人
        private String ebcName;//电商企业的登记名称,对应清单的收发货人
        private String logisticsCode;//物流企业的海关注册登记编号或统一社会信用代码
        private String logisticsName;//物流企业的登记名称
        private String statisticsFlag;//申报业务类型A:简化申报;B:汇总申报。
        private String country;//运抵国(地区)
        private String pod;//指运港(目的港)
        private String billNo;//提运单号
        private Integer logisticsTotal;//分运单总数
        private Integer totalPackNo;//总件数
        private BigDecimal totalGoodsValue;//总价值,单位元
        private BigDecimal totalGrossWeight;//总重量,单位KG
        private String customsCode;//申报地海关代码
        private String currency;//币制，海关标准的货币代码
        private String portCode;//(货物实际)出口口岸代码
        private String ieFlag;//I进口，E出口
        private String ieDate;//出口日期,YYYYMMDD
        private String trafMode;//运输方式
        private String domesticTrafNo;//境内运输工具编号,如车牌号
        private String goodsInfo;//主要品名
        private String voyageNo;//航班航次号
        private Integer status;//状态 0:取消,1:正常;
        private String createTime;//创建时间
        private String updateTime;//修改时间

}
