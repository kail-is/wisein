package com.wisein.wiselab.config;

import com.wisein.wiselab.config.interceptor.channel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final HandlerInterceptor authInterceptor;
    private final HandlerInterceptor channel;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(channel)
                .addPathPatterns("/*")
                .excludePathPatterns("/resources/*");

        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/user/*")
                .excludePathPatterns();
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        commonsMultipartResolver.setMaxUploadSizePerFile(5 * 1024 * 1024);
        return commonsMultipartResolver;
    }

}