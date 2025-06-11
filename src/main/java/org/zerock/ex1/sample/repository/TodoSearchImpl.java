package org.zerock.ex1.sample.repository;

import com.querydsl.jpa.JPQLQuery;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.ex1.sample.entity.QTodoEntity;
import org.zerock.ex1.sample.entity.TodoEntity;

@Log4j2
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    public TodoSearchImpl() {
        super(TodoEntity.class);
    }

    @Override
    public Page<TodoEntity> search1(Pageable pageable) {
        log.info("search1...............");

        QTodoEntity todoEntity = QTodoEntity.todoEntity;

        JPQLQuery<TodoEntity> query = from(todoEntity);
        query.where(todoEntity.mno.gt(0L));

        getQuerydsl().applyPagination(pageable, query);

        List<TodoEntity> entityList = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(entityList, pageable, count);
    }
}
