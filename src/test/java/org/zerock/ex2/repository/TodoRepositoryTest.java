package org.zerock.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex2.entity.TodoEntity;

import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testInsert() {
        TodoEntity todoEntity = TodoEntity.builder()
                .title("부트 끝내기 ")
                .writer("user00")
                .dueDate(LocalDate.of(2025, 12, 23))
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
                    .dueDate(LocalDate.of(2025, 11, 30))
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
    @Transactional
    public void testRead2() {
        Long mno = 55L;

        Optional<TodoEntity> result = todoRepository.findById(mno);

        result.ifPresent(todoEntity -> {
            System.out.println(todoEntity);
        });
    }
}