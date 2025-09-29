package org.zerock.ex1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.ex1.service.SampleService;

@RestController
@RequestMapping("/api/v1/sample")
@Log4j2
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;

    @RequestMapping("/hello")
    public String[] hello() {
        return new String[]{"Hello", "World"};
    }
}
