package com.rubypaper.ex1.sample.repository;

import com.rubypaper.ex1.sample.dto.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA CRUD + Paging
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

}
