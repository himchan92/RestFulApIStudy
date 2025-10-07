package org.zerock.ex2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.ex2.dto.TodoDTO;
import org.zerock.ex2.entity.TodoEntity;
import org.zerock.ex2.repository.search.TodoSearch;

import java.util.Optional;

//JpaRepository : JPA CRUD + PAGING
public interface TodoRepository extends JpaRepository<TodoEntity, Long>, TodoSearch {

    @Query("select t from TodoEntity t ")
    Page<TodoEntity> listAll(Pageable pageable);

    //TODO: 파라미터 지정 JPQL, @Param으로 매핑하며 : 붙어야함
    @Query("select t from TodoEntity t " +
            " where t.title like %:keyword% and t.mno > 0 order by t.mno desc")
    Page<TodoEntity> listOfTitle(@Param("keyword") String keyword, Pageable pageable);

    @Query("select t from TodoEntity  t where t.mno = :mno")
    Optional<TodoDTO> getDTO(@Param("mno") Long mno);
}