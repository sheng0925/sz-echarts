package com.atjiumi.es.mapper;

import com.atjiumi.es.entity.BcoDeclarationStatistics;
import org.mapstruct.Mapper;

import java.util.List;


public interface BcoDeclarationStatisticsMapper {

    /**
     * 添加数据到总记录表中
     * @param list
     * @return
     */
    int InsertBcoDeclarationStatistics(List<BcoDeclarationStatistics> list);

    /**
     * 通过id查询某一条的数据
      * @return
     */
    BcoDeclarationStatistics selectById();


    /**
     * 删除20201009号的数据
     */
    int DeleteNine();

}
