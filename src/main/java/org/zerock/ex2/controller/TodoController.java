package org.zerock.ex2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zerock.ex2.entity.TodoDTO;
import org.zerock.ex2.service.TodoService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/todos")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoDTO> register(@RequestBody @Validated TodoDTO todoDTO, BindingResult bindingResult) {
        log.info("register............");
        log.info(todoDTO);

        if(bindingResult.hasErrors()) {
            log.info(bindingResult.getAllErrors());
            return ResponseEntity.badRequest().build();
        }

        //번호는 null인경우 insert
        todoDTO.setMno(null);
        return ResponseEntity.ok(todoService.register(todoDTO));
    }

    @GetMapping("/{mno}")
    public ResponseEntity<TodoDTO> read(@PathVariable("mno") Long mno) {
        log.info("read...........");
        log.info(mno);

        return ResponseEntity.ok(todoService.read(mno));
    }

    @PutMapping("/{mno}")
    public ResponseEntity<TodoDTO> modify(@PathVariable("mno") Long mno, @RequestBody TodoDTO todoDTO) {

        log.info("modify..........");
        log.info(mno);
        log.info(todoDTO);
        todoDTO.setMno(mno);

        TodoDTO modifiedTodoDTO = todoService.modify(todoDTO);

        return ResponseEntity.ok(modifiedTodoDTO);
    }

    @DeleteMapping("/{mno}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable("mno") Long mno) {
        log.info("remove...............");
        log.info(mno);

        todoService.remove(mno);
        Map<String, String> result = Map.of("result", "success");

        return ResponseEntity.ok(result);
    }
}