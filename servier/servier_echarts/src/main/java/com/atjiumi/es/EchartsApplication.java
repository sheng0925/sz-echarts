package com.atjiumi.es;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author 盛镇林
 * @date 2020/7/18 - 14:41
 */

@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = {"com.atjiumi"})//改变扫描规则只要是com.atguigu包下面的都扫描
@MapperScan(value = "com.atjiumi.es.mapper")
public class EchartsApplication {


    public static void main(String[] args) {

        SpringApplication.run(EchartsApplication.class, args);

    }

}
