package org.zerock.ex1.sample.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.ex1.sample.entity.TodoEntity;

public interface TodoSearch {

    Page<TodoEntity> search1(Pageable pageable);
}
