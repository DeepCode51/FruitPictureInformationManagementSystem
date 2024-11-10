package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * application 启动类，也就是传说中的spring boot 启动类了
 */
@SpringBootApplication
@MapperScan(basePackages = { "com.spring.dao" }) // mapper 扫描一下这个包
public class BootApplication {

    public static ConfigurableApplicationContext content = null;

    public static void main(String[] args) {
        content = SpringApplication.run(BootApplication.class, args);
    }
}
