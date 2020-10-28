package com.atjiumi.demo;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringTask {

    //@Scheduled(cron = "*/5 * * * * *")
    public void taskOne(){

        System.out.println("这是我的第一个定时任务");

    }
}
