package org.zerock.ex2.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.ex2.dto.TodoDTO;
import org.zerock.ex2.service.TodoService;

@Log4j2
@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("")
    public ResponseEntity<TodoDTO> register(@RequestBody @Validated TodoDTO todoDTO, BindingResult bindingResult) {

        log.info("register..............");
        log.info(todoDTO);

        if(bindingResult.hasErrors()) {
            log.info(bindingResult.getAllErrors());
            return ResponseEntity.badRequest().build();
        }

        return null;
    }
}
