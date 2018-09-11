package com.huijava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.treeleafj.xdoc.boot.EnableXDoc;

/**
 * 加上EnableXDoc以便启用XDOC在线HTML文档
 */
@EnableXDoc
@SpringBootApplication
@tk.mybatis.spring.annotation.MapperScan("com.huijava.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
