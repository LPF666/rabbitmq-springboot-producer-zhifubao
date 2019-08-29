package com.bfxy.springboot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * Created by lipf on 2019/4/8.
 */
@Configuration
@MapperScan({"com.bfxy.springboot.dao"})
public class MyBatisConfig {
}
