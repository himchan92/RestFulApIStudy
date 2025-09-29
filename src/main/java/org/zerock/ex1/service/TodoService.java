package org.zerock.ex1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex1.dto.TodoDTO;
import org.zerock.ex1.entity.TodoEntity;
import org.zerock.ex1.exception.EntityNotFoundException;
import org.zerock.ex1.repository.TodoRepository;

import java.util.Optional;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoDTO register(TodoDTO todoDTO) {

        //DTO -> Entity
        TodoEntity todoEntity = todoDTO.todoEntity();

        todoRepository.save(todoEntity);

        //DTO 반환
        return new TodoDTO(todoEntity);
    }

    public TodoDTO read(Long mno) {
        Optional<TodoDTO> result = todoRepository.getDTO(mno);

        TodoDTO todoDTO = result.orElseThrow(() -> new EntityNotFoundException("Todo " + mno + " not found"));

        return todoDTO;
    }
}
