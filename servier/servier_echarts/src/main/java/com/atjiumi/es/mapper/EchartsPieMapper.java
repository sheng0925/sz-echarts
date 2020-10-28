package com.atjiumi.es.mapper;

import com.atjiumi.es.entity.EchartsMap;
import com.atjiumi.es.entity.EchartsPie;
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
public interface EchartsPieMapper extends BaseMapper<EchartsPie> {

    /**
     * 在总表中查询历史运输方式各占多少比例
     * @return
     */
    List<EchartsPie> selectPieTopThree();


    /**
     * 添加今天运输方式各占多少比例到运输表中
     * @param list
     * @return
     */
    int INSERTPieTodayTop(List<EchartsPie> list);

    /**
     * 删除运输表中的所有数据
     * @return
     */
    int deletePieAll();


    /**
     * 查询交通表今天的数据;
     * @return
     */
    List<EchartsPie> selectTodayTraffic();
}
