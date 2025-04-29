package com.rubypaper.ex1.sample.repository;

import com.rubypaper.ex1.sample.dto.TodoEntity;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@DataJpaTest // DB ONLY TEST
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testInsert() {
        TodoEntity todoEntity = TodoEntity.builder()
            .title("부트 끝내기")
            .writer("user00")
            .dueDate(LocalDate.of(2025, 4, 29))
            .build();

        todoRepository.save(todoEntity);

        System.out.println("New TodoEntity MNO: " + todoEntity.getMno());
    }

    @Test
    public void testInsertDummies() {
        for(int i = 0; i < 100; i++) {
            TodoEntity todoEntity = TodoEntity.builder()
                .title("Test Todo..." + i)
                .writer("tester" + i)
                .dueDate(LocalDate.of(2025, 5, 1))
                .build();

            todoRepository.save(todoEntity);
            System.out.println("New TodoEntity MNO: " + todoEntity.getMno());
        }
    }

    @Test
    public void testRead() {
        Long mno = 58L;

        Optional<TodoEntity> result = todoRepository.findById(mno);

        //Optional 제공 존재유무 체크
        result.ifPresent(todoEntity -> {
            System.out.println(todoEntity);
        });
    }

    @Test
    @Transactional
    public void testRead2() {
        Long mno = 55L;

        Optional<TodoEntity> result2 = todoRepository.findById(mno);

        //Optional 제공 존재유무 체크
        result2.ifPresent(todoEntity -> {
            System.out.println(todoEntity);
        });
    }

    @Test
    @Transactional
    @Commit //트랜젝션내에서는 수행 후 자동 롤백으로 반영결과 보고싶은경우(운영에서는 비추)
    public void testUpdateDirtyCheck() {
        Long mno = 58L;

        Optional<TodoEntity> result = todoRepository.findById(mno);

        TodoEntity todoEntity = result.get();

        System.out.println("OLD: " + todoEntity);

        todoEntity.changeTitle("Change Title..." + Math.random());
        todoEntity.changeWriter("Change Writer..." + Math.random());

        System.out.println("Changed : " + todoEntity);
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
}