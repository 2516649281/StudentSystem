package com.chunfeng;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

/**
 * 主启动类
 *
 * @author by 春风能解释
 * <p>
 * 2022/10/20
 */
@SpringBootApplication
@MapperScan("com.chunfeng.mapper")
@EnableCaching
@Slf4j
public class StudentSystemApplication {

    /**
     * 主启动方法
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        ApplicationContext run = SpringApplication.run(StudentSystemApplication.class, args);
        log.info("{}:主启动类启动完成!", run.getClass().getSimpleName());
    }

}
