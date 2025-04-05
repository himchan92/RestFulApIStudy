package org.zerock.ex1.sample.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.ex1.sample.entity.TodoEntity;

// JPA CRUD API 상속
// CrudRepository ( CRUD ), JpaRepository ( CRUD + PAGING )
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

    @Query(
        value = "select t from TodoEntity t",
        countQuery = " select count(*) from tbl_todos ",
        nativeQuery = true
    )
    Page<TodoEntity> listAll(Pageable pageable);

    //파라미터 셋팅하여 설정도 가능
    //복잡한 설정, 조인처리로 실무에서는 잘 안씀
    @Query(
        "select t from TodoEntity t " +
            " where t.title like %:keyword% and t.mno > 0 order by t.mno desc"
    )
    Page<TodoEntity> listOfTitle(@Param("keyword") String keyword, Pageable pageable);
}
