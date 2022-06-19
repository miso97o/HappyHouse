package com.ssafy.happyhouse.config;

import java.util.Arrays;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@MapperScan(basePackages = "com.ssafy.happyhouse.model.dao") //mybatis 스캔 태그를 이용해 repo interface들의 위치를 지정했던것을 스프링 부트에서는 이렇게
public class WebMvcConfiguration implements WebMvcConfigurer {


}
