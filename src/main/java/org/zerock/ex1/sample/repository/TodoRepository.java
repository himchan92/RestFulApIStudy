package org.zerock.ex1.sample.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.ex1.sample.entity.TodoEntity;

// JpaRepository : JPA CRUD + PAGING
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

    // where 절 및 복잡한 쿼리에는 적합하지 않아 실무에서는 많이 안쓰고 복잡한 쿼리부분은 QueryDsl 사용함
    @Query("select t from TodoEntity t ")
    Page<TodoEntity> listAll(Pageable pageable);
}
