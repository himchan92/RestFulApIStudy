package org.zerock.ex1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.ex1.dto.TodoDTO;
import org.zerock.ex1.entity.TodoEntity;

public interface TodoSearch {

    Page<TodoEntity> search1(Pageable pageable);

    Page<TodoDTO> searchDTO(Pageable pageable);
}
