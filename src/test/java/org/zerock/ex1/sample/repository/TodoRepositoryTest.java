package org.zerock.ex1.sample.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex1.sample.entity.TodoEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testInsert() {
        TodoEntity todoEntity = TodoEntity.builder()
            .title("부트끝내기")
            .writer("user00")
            .dueDate(LocalDate.of(2025, 4, 5))
            .build();

        todoRepository.save(todoEntity);

        System.out.println("New TodoEntity mno: " + todoEntity.getMno());
    }

    @Test
    @Transactional //한 트랜젝션 작업설정으로 동일 SQL 수행 시 한개만 수행
    public void testRead() {
        Long mno = 1L;

        Optional<TodoEntity> result = todoRepository.findById(mno);

        result.ifPresent(todoEntity -> {
            System.out.println(todoEntity);
        });
    }

    @Test
    @Transactional
    @Commit //트랜젝션 기본은 수행종료 후 롤백하나 결과를 보고싶을경우 임시설정하여 볼수있음
    public void testUpdateDirtyCheck() {
        Long mno = 1L;

        Optional<TodoEntity> result = todoRepository.findById(mno);

        TodoEntity todoEntity = result.get();

        System.out.println("OLD: " + todoEntity);

        //변경 작업 시 Transactional에 의해 Dirty Checking 발생하고 save 호출시 변경감지로 인한 JPA UPDATE 수행
        todoEntity.changeTitle("Change Title: " + Math.random());
        todoEntity.changeWriter("Change Writer: " + Math.random());
        System.out.println("Changed : " + todoEntity);

        todoRepository.save(todoEntity); //이 작업 안하면 UPDATE 수행안됨
    }

    @Test
    @Transactional
    @Commit
    public void testDelete() {
        Long mno = 1L;

        //삭제전 조회하여 확인도 가능
        //todoRepository.deleteById(mno);

        Optional<TodoEntity> result = todoRepository.findById(mno);

        result.ifPresent(todoEntity -> {
            todoRepository.delete(todoEntity);
        });
    }

    //페이징처리지원
    @Test
    public void testPaging() {
        //mno 기준 0 ~ 10 단위 내림차순 페이징
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<TodoEntity> result = todoRepository.findAll(pageable);
        System.out.println(result.getTotalPages());
        System.out.println(result.getTotalElements());

        List<TodoEntity> todoEntityList = result.getContent();

        todoEntityList.forEach(todoEntity -> {
            System.out.println(todoEntity);
        });
    }
}