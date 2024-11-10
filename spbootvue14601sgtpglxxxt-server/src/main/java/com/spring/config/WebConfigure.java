package com.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigure implements WebMvcConfigurer {

    /**
     * 设置上传路径存储位置,默认放在运行目录下的 upload
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 设置方位地址
        System.out.println(Configure.UPLOAD_DIR);
        registry
            .addResourceHandler("/upload/**")
            .addResourceLocations("file:" + Configure.UPLOAD_DIR)
            .addResourceLocations("file:" + System.getProperty("user.dir") + "/src/webapp/upload/");
    }
}
