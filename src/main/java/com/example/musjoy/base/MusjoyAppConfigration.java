package com.example.musjoy.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MusjoyAppConfigration implements WebMvcConfigurer {

    @Bean
    LoginInterceptor localInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(localInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/error","/login/*","/pages/**","/index/*",
                        "/webjars/**","/swagger-resources/**","/v2/api-docs","/configuration/**","/swagger-ui.html");//Swagger2 接口API文档资源放行
    }
}
