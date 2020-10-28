package com.atjiumi.es.mapper;

import com.atjiumi.es.entity.EchartsMap;
import com.atjiumi.es.entity.EchartsNavigation;
import com.atjiumi.es.entity.EchartsOrder;
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
public interface EchartsNavigationMapper extends BaseMapper<EchartsNavigation> {
    /**
     * 查询历史贸易额度，按照申报地区分，深圳机场快件，前海保税
     * @return
     */
    List<EchartsNavigation> selectHistoryData();


    /**
     * 添加到导航栏总金额表中
     * @param list
     * @return
     */
    int insertEchartsEchartsNavigation(List<EchartsNavigation> list);

    /**
     * 删除导航栏总金额表中所有数据
     */

    int deleteEchartsEchartsNavigation();


    /**
     * 查询导航栏总金额表中的数据
     */
    List<EchartsNavigation> selectEchartsEchartsNavigation();


}
