package org.zerock.ex1.sample.service;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.zerock.ex1.sample.dto.PageRequestDTO;
import org.zerock.ex1.sample.dto.TodoDTO;

@SpringBootTest
class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    @Test
    public void todoRegister() {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setTitle("Test Todo");
        todoDTO.setWriter("user00");
        todoDTO.setDueDate(LocalDate.of(2025,6,13));

        TodoDTO resultDTO = todoService.register(todoDTO);

        System.out.println(resultDTO);
    }

    @Test
    public void testRead() {
        Long mno = 100L;

        TodoDTO todoDTO = todoService.read(mno);

        System.out.println(todoDTO);
    }

    @Test
    public void testRemove() {
        Long mno = 3L;

        todoService.remove(mno);
    }

    @Test
    public void testModify() {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setMno(102L);
        todoDTO.setTitle("수정된 제목");
        todoDTO.setWriter("fix1");
        todoDTO.setDueDate(LocalDate.now());

        todoService.modify(todoDTO);
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        Page<TodoDTO> result = todoService.getList(pageRequestDTO);

        System.out.println("PREV: " + result.previousPageable());
        System.out.println("NEXT: " + result.nextPageable());
        System.out.println("TOTAL: " + result.getTotalElements());

        result.getContent().forEach(todoDTO -> System.out.println(todoDTO));
    }
}