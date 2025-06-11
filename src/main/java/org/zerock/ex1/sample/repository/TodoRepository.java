package org.zerock.ex1.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.ex1.sample.entity.TodoEntity;

// JPA CRUD + PAGING 제공
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}
