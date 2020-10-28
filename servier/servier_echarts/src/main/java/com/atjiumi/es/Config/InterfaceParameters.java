package com.atjiumi.es.Config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "interface-parameters" ,ignoreUnknownFields = false)
@PropertySource(value = "classpath:config/interface-parameters.properties",encoding = "UTF-8")
@Data
@Component
public class InterfaceParameters {
       //接口请求url地址
       private String url;
       //token的名称
       private String tokenName;
       //token的值
       private String tokenValue;

}
