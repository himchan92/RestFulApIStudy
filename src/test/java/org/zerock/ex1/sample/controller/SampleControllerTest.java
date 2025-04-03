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

    @Autowired(required = false)
    private TestRestTemplate testRestTemplate;

    @Test
    public void testHello() {
        String[] result = testRestTemplate.getForObject(
            "/api/v1/sample/hello",
            String[].class
        );

        log.info(Arrays.toString(result));
    }
}