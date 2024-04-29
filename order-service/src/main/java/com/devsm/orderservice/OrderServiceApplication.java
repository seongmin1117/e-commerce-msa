package com.devsm.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@EnableDiscoveryClient
@EnableJpaAuditing // 생성,수정 자동 관리
@SpringBootApplication(scanBasePackages = {"com.devsm.commonserver"})
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}
