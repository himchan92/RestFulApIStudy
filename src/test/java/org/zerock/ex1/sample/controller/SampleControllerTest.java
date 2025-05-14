package org.zerock.ex1.sample.controller;

import java.util.Arrays;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Log4j2
class SampleControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate; //스프링부트 REST 방식 테스트 지원

    @Test
    public void testHello() {
        //getForObject : GET 방식동작 결과데이터 객체변환
        String[] result = testRestTemplate.getForObject("/api/v1/sample/hello", String[].class);
        log.info(Arrays.toString(result));
    }
}