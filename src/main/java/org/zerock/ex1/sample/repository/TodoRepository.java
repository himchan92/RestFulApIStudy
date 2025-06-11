package org.zerock.ex1.sample.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.ex1.sample.dto.TodoDTO;
import org.zerock.ex1.sample.entity.TodoEntity;

// JPA CRUD + PAGING 제공
public interface TodoRepository extends JpaRepository<TodoEntity, Long>, TodoSearch {
    @Query("select t from TodoEntity t ")
    Page<TodoEntity> listAll(Pageable pageable);

    @Query("select t from TodoEntity t where t.mno = :mno")
    Optional<TodoDTO> getDTO(@Param("mno") Long mno);
}
