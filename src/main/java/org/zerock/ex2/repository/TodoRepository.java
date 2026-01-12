package org.zerock.ex2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.ex2.entity.TodoDTO;
import org.zerock.ex2.entity.TodoEntity;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<TodoEntity, Long>, TodoSearch {

    @Query("select t from TodoEntity t ")
    Page<TodoEntity> listAll(Pageable pageable);

    @Query("select t from TodoEntity t where t.mno = :mno")
    Optional<TodoDTO> getDTO(@Param("mno") Long mno);
}