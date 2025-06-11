package org.zerock.ex1.sample.repository;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex1.sample.entity.TodoEntity;

import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest //SpringBootTest와 달리 엔티티 같은 DB 부분만 실행
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testInsert() {
        TodoEntity todoEntity = TodoEntity.builder()
                .title("부트 공부")
                .writer("user00")
                .dueDate(LocalDate.of(2025,6,11))
                .build();

        todoRepository.save(todoEntity); //저장

        System.out.println("New TodoEntity MNO: " + todoEntity.getMno());
    }

    @Test
    public void testInsertDummies() {
        for(int i = 0; i < 100; i++) {
            TodoEntity todoEntity = TodoEntity.builder()
                    .title("Test Todo..." + i)
                    .writer("tester" + i)
                    .dueDate(LocalDate.of(2025,6,11))
                    .build();

            todoRepository.save(todoEntity);

            System.out.println("New TodoEntity MNO: " + todoEntity.getMno());
        }
    }

    @Test
    public void testRead() {
        Long mno = 58L;

        Optional<TodoEntity> result = todoRepository.findById(mno);

        result.ifPresent(todoEntity -> {
            System.out.println(todoEntity);
        });
    }

    @Test
    @Transactional //한트랜젝션 수행설정으로 동일 쿼리 중복수행안함
    public void testRead2() {
        Long mno = 58L;

        Optional<TodoEntity> result = todoRepository.findById(mno);

        result.ifPresent(todoEntity -> {
            System.out.println(todoEntity);
        });
    }

    @Test
    @Transactional
    @Commit
    public void testUpdateDirtyCheck() {
        Long mno = 58L;

        Optional<TodoEntity> result = todoRepository.findById(mno);

        TodoEntity todoEntity = result.get();

        System.out.println("OLD: " + todoEntity);

        todoEntity.changeTitle("Change Title..." + Math.random());
        todoEntity.changeWriter("Change Writer..." + Math.random());

        System.out.println("Changed: " +todoEntity);

        //todoRepository.save(todoEntity); // @Transactional 없으면 별도 save 호출안할시 실제 DB반영안된고 영속성컨텍스트에 담겨있음
    }

    @Test
    @Transactional
    @Commit
    public void testDelete() {
        Long mno = 101L;

        Optional<TodoEntity> result = todoRepository.findById(mno);

        result.ifPresent(todoEntity -> {
            todoRepository.delete(todoEntity);
        });
    }

    @Test
    public void testPaging() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<TodoEntity> result = todoRepository.findAll(pageable);

        System.out.println(result.getTotalPages());
        System.out.println(result.getTotalElements());

        List<TodoEntity> todoEntityList = result.getContent();

        todoEntityList.forEach(todoEntity -> {
            System.out.println(todoEntity);
        });
    }

    @Test
    public void testListAll() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<TodoEntity> result = todoRepository.listAll(pageable);

        System.out.println(result.getContent());
    }

    @Test
    public void testSearch1() {
        //페이징처리 : 0 ~ 10 mno 기준 내림차순
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<TodoEntity> result = todoRepository.search1(pageable);

        System.out.println(result.getTotalPages());
        System.out.println(result.getTotalElements());

        List<TodoEntity> todoEntityList = result.getContent();

        todoEntityList.forEach(todoEntity -> {
            System.out.println(todoEntity);
        });
    }
}