package org.zerock.ex2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.ex2.entity.TodoEntity;

//JpaRepository : CRUD + Paging
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}
