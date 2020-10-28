package com.atjiumi.demo;

import com.atjiumi.es.mapper.BcoDeclarationStatisticsMapper;
import com.atjiumi.es.service.EchartsBrokenLineGraphService;
import com.atjiumi.es.service.impl.EchartsBrokenLineGraphServiceImpl;
import com.atjiumi.es.utils.ApplicationContextUtils;
import com.atjiumi.es.utils.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.TimerTask;

/**
 * @author 盛镇林
 * @date 2020/9/18 - 13:58
 */
@RestController
@Component
public class TimingTaskTest extends TimerTask {
//implements ServletContextListener
    /*@Autowired
    BcoDeclarationStatisticsMapper bcoDeclarationStatisticsMapper ;*/
    private BcoDeclarationStatisticsMapper bd;


//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        this.bd= WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()).getBean(BcoDeclarationStatisticsMapper.class);
//    }

    @Override
    public void run() {

        BcoDeclarationStatisticsMapper bdaaa= ApplicationContextUtils.getBean(BcoDeclarationStatisticsMapper.class);
        System.out.println("我有一头小毛驴");
        System.out.println(bdaaa);
        //NewOrderTest newOrderTest= ApplicationContextUtils.getBean(NewOrderTest.class);

        System.out.println("执行万不");
        //1.调用接口方法获取数据库最新数据,更新到本机数据库
        //2.将每个模块数据筛选最终存入到对应的表中
        //3.页面展示数据从对应的表中获取
    }
}
