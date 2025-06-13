package org.zerock.ex1.sample.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.ex1.sample.dto.TodoDTO;
import org.zerock.ex1.sample.service.TodoService;

@RestController
@Log4j2
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("")
    public ResponseEntity<TodoDTO> register(@RequestBody TodoDTO todoDTO,
        BindingResult bindingResult) {
        log.info("register....................");
        log.info(todoDTO);

        if(bindingResult.hasErrors()) {
            log.info(bindingResult.getAllErrors());
            return ResponseEntity.badRequest().build();
        }

        return null;
    }

    @GetMapping("/{mno}")
    public ResponseEntity<TodoDTO> read(@PathVariable("mno") Long mno) {
        log.info("read....................");
        log.info(mno);

        return ResponseEntity.ok(todoService.read(mno));
    }
}