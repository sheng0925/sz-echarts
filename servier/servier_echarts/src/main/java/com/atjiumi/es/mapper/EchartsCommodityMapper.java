package com.atjiumi.es.mapper;

import com.atjiumi.es.entity.EchartsCircle;
import com.atjiumi.es.entity.EchartsCommodity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-07-17
 */
public interface EchartsCommodityMapper extends BaseMapper<EchartsCommodity> {
    /**
     * 查询热销商品前五
     * @return
     */
    List<EchartsCommodity> selecCommodityTopFive();

    /**
     *添加热销商品到热销表中
     */
    int insertEchartsCommodity(List<EchartsCommodity> list);

    /**
     * 删除热销表中的所有数据
     */
    int deleteEchartsCommodity();

    /**
     * 查询热销表中的所有数据
     */
    List<EchartsCommodity> selectEchartsCommodity();

}
