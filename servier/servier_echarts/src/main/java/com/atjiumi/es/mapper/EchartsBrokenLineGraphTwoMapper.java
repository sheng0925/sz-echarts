package com.atjiumi.es.mapper;

import com.atjiumi.es.entity.EchartsBrokenLineGraph;
import com.atjiumi.es.entity.EchartsBrokenLineGraphTwo;
import com.atjiumi.es.entity.seed.BrokenLineGraphTwoSubclass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-07-17
 */
public interface EchartsBrokenLineGraphTwoMapper extends BaseMapper<EchartsBrokenLineGraphTwo> {
    /**
     * 查询今年每个月订单数量
     * @return
     */
    List<EchartsBrokenLineGraphTwo> selectBrokenLineTwo();


    /**
     * 查询去年每个月的订单数量
     */
    List<EchartsBrokenLineGraphTwo> selectLastMonth();


    /**
     * 查询一年的数据每个月走了多少车
     *         SELECT * FROM ( SELECT COUNT(*) ,ie_date FROM ( SELECT  bill_no,DATE_FORMAT(ie_date,'%Y-%m') AS ie_date FROM
     *               bco_declaration_statistics WHERE DATE_FORMAT(ie_date,'%Y')=YEAR(NOW())
     *                GROUP BY bill_no,DATE_FORMAT(ie_date,'%Y-%m')) AS j
     *               GROUP BY j.ie_date) AS k ORDER BY k.ie_date ASC
     */

    /**
     * 添加历史数据到历史数据表中
     */
    int insertEchartsBrokenLineTwo(List<EchartsBrokenLineGraphTwo> list);

    /**
     * 删除历史表中的所有数据
     */
    int deleteEchartsBrokenLineTwo();

    /**
     * 查询本表2020的数据
     */
    List<EchartsBrokenLineGraphTwo> selectEchartsBrokenlineTwoTwentyTwenty();

    /**
     * 查询本表2019的数据
     */
    List<EchartsBrokenLineGraphTwo> selectEchartsBrokenlineTwoTwentyNineteen();
}
