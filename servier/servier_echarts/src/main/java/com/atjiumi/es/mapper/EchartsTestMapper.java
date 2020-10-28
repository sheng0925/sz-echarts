package com.atjiumi.es.mapper;

import com.atjiumi.es.entity.EchartsTest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author 盛镇林
 * @date 2020/9/9 - 22:30
 */
public interface EchartsTestMapper extends BaseMapper<EchartsTest> {

    EchartsTest selectByName(@Param("name") String name);

    /**
     * 查询累计目的国前八的国家
     * @return
     */
    List<EchartsTest> selectAll();


    /**
     * 添加数据到目的国表中
     */
    int insertEchartsTest(List<EchartsTest> list);


    /**
     * 删除目的国的所有数据
     * @return
     */
    int deleteEchartsTest();

    /**
     * 查询目的国表中的所有数据
     */
    List<EchartsTest> selectEchartsTest();

}
