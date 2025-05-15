package org.zerock.ex1.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.ex1.sample.entity.TodoEntity;

// JpaRepository : JPA CRUD + PAGING
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

}
