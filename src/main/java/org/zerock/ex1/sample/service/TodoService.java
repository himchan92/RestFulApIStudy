package org.zerock.ex1.sample.service;

import java.util.Optional;
import javax.swing.text.html.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex1.sample.dto.TodoDTO;
import org.zerock.ex1.sample.entity.TodoEntity;
import org.zerock.ex1.sample.exception.EntityNotFoundException;
import org.zerock.ex1.sample.repository.TodoRepository;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoDTO register(TodoDTO todoDTO) {

        //DTO -> Entity
        TodoEntity todoEntity = todoDTO.toEntity();

        todoRepository.save(todoEntity);

        return new TodoDTO(todoEntity);
    }

    public TodoDTO read(Long mno) {
        Optional<TodoDTO> result = todoRepository.getDTO(mno);

        TodoDTO todoDTO = result.orElseThrow(() -> new EntityNotFoundException("Todo " + mno + " not found"));

        return todoDTO;
    }

    public void remove(Long mno) {
        Optional<TodoEntity> result = todoRepository.findById(mno);

        TodoEntity todoEntity = result.orElseThrow(
            () -> new EntityNotFoundException("Todo " + mno + " not found")
        );

        todoRepository.delete(todoEntity);
    }

    public TodoDTO modify(TodoDTO todoDTO) {
        Optional<TodoEntity> result = todoRepository.findById(todoDTO.getMno());

        TodoEntity todoEntity = result.orElseThrow(() -> new EntityNotFoundException("Todo " + todoDTO.getMno() + " not found"));

        todoEntity.changeTitle(todoDTO.getTitle());
        todoEntity.changeWriter(todoDTO.getWriter());
        todoEntity.changeDueDate(todoDTO.getDueDate());

        return new TodoDTO(todoEntity);
    }
}
