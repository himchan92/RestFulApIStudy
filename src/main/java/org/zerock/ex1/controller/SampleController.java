package org.zerock.ex1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.ex1.service.SampleService;

@Log4j2
@RestController
@RequestMapping("/api/v1/sample")
@RequiredArgsConstructor //final 대상 생성자 DI 지원
public class SampleController {

    private final SampleService sampleService;

    @RequestMapping("/hello")
    public String[] hello() {
        return new String[]{"Hello", "World"};
    }
}
