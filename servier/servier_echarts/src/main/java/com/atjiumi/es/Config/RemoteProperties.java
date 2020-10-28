package com.atjiumi.es.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 用于读取自定义properties文件
 * @author 盛镇林
 * @date 2020/9/18 - 20:13
 */
@Configuration
@ConfigurationProperties(prefix = "cus-constant",ignoreUnknownFields = false)
@PropertySource(value = "classpath:config/cus-constant.properties",encoding= "UTF-8")
@Data
@Component
public class RemoteProperties {
    //目的国
    private String street;

    //运输方式
    private String declareCode;

    //申报地海关
    private String customs;

    //币制
    private String currency;

}
