package org.zerock.ex1.sample.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.ex1.sample.entity.TodoEntity;

// JPA CRUD + PAGING 제공
public interface TodoRepository extends JpaRepository<TodoEntity, Long>, TodoSearch {
    @Query("select t from TodoEntity t")
    Page<TodoEntity> listAll(Pageable pageable);
}
