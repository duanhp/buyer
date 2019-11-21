package com.duan.buyer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.duan.buyer.dao")
@SpringBootApplication
public class BuyerApplication {


    public static void main(String[] args) {
        SpringApplication.run(BuyerApplication.class, args);
    }

}
