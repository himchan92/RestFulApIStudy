package org.zerock.ex1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.ex1.dto.TodoDTO;
import org.zerock.ex1.entity.TodoEntity;
import org.zerock.ex1.repository.search.TodoSearch;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<TodoEntity, Long>, TodoSearch {

    @Query(value = "select * from tbl_todos t ",
            countQuery = " select count(*) from tbl_todos ", //갯수지정쿼리사용
            nativeQuery = true //DB 함수 허용여부
    )
    Page<TodoEntity> listAll(Pageable pageable);

    @Query("select t from TodoEntity t where t.mno = :mno")
    Optional<TodoDTO> getDTO(@Param("mno") Long mno);

    //@Param 지정해서 변수명 지정
    //파라미터는 앞에 : 붙여 매핑
    @Query("select t from TodoEntity t " +
            " where t.title like %:keyword% and t.mno > 0 order by t.mno desc"
    )
    Page<TodoEntity> listOfTitle(@Param("keyword") String keyword, Pageable pageable);
}
