package com.wisein.wiselab.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import com.wisein.wiselab.config.interceptor.CheckWriterInterceptor;
import com.wisein.wiselab.config.interceptor.channel;
import com.wisein.wiselab.dto.MemberDTO;
import com.wisein.wiselab.service.MatzipService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final HandlerInterceptor authInterceptor;
    private final HandlerInterceptor channel;

    private final ObjectMapper objectMapper;

    private MemberDTO memberDTO;

    @Autowired
    private MatzipService matzipService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(channel)
                .addPathPatterns("/*")
                .excludePathPatterns("/resources/**");

        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/*")
                .excludePathPatterns("/")
                .excludePathPatterns("/authCheck")
                .excludePathPatterns("/login")
                .excludePathPatterns("/register")
                .excludePathPatterns("/qalist")
                .excludePathPatterns("/error/*");

        CheckWriterInterceptor checkWriterInterceptor = new CheckWriterInterceptor(memberDTO, matzipService);
        InterceptorRegistration reg = registry.addInterceptor(checkWriterInterceptor);
        reg.addPathPatterns("/updRecm", "putRecm", "/delRecm");
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        commonsMultipartResolver.setMaxUploadSizePerFile(5 * 1024 * 1024);
        return commonsMultipartResolver;
    }

    @Bean
    public FilterRegistrationBean xssFilterBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new XssEscapeServletFilter());
        registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
        registrationBean.addUrlPatterns("*.do", "*.jsp");
        return registrationBean;
    }

    @Bean
    public MappingJackson2HttpMessageConverter jsonEscapeConverter() {
        ObjectMapper copy = objectMapper.copy();
        copy.getFactory().setCharacterEscapes(new HtmlCharacterEscapes());
        return new MappingJackson2HttpMessageConverter(copy);
    }

}