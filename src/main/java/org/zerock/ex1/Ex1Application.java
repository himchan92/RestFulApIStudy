package org.zerock.ex1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //엔티티 시간자동처리 지원
public class Ex1Application {

    public static void main(String[] args) {
        SpringApplication.run(Ex1Application.class, args);
    }

}