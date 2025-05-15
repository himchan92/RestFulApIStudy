package org.zerock.ex1.sample.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex1.sample.dto.TodoDTO;
import org.zerock.ex1.sample.entity.TodoEntity;
import org.zerock.ex1.sample.repository.TodoRepository;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoDTO register(TodoDTO todoDTO) {
        TodoEntity todoEntity = todoDTO.toEntity();

        todoRepository.save(todoEntity);

        return new TodoDTO(todoEntity);
    }

    public TodoDTO read(Long mno) {
        //Optional<TodoDTO> result = todoRepository.getDTO(mno);
        return null;
    }
}
