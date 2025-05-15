package org.zerock.ex1.sample.repository;

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
import org.zerock.ex1.sample.entity.TodoEntity;

@DataJpaTest //Entity 처럼 DB 관련 부분한 테스트 범위 설정
@AutoConfigureTestDatabase(replace = Replace.NONE) //실제 DB 미사용
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testInsert() {
        TodoEntity todoEntity = TodoEntity.builder()
            .title("부트 테스트")
            .writer("user00")
            .dueDate(LocalDate.of(2025, 05, 15))
            .build();

        todoRepository.save(todoEntity); // JPA SAVE

        System.out.println("New TodoEntity MNO: " + todoEntity.getMno());
    }

    @Test
    public void testInsertDummies() {
        for(int i = 0; i < 100; i++) {
            TodoEntity todoEntity = TodoEntity.builder()
                .title("Test Todo..." + i)
                .writer("Tester" + i)
                .dueDate(LocalDate.of(2025, 05, 15))
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
    @Transactional //동일 영속성 컨텍스트 유지, 중복 조회 방지
    public void testRead2() {
        Long mno = 58L;

        Optional<TodoEntity> result = todoRepository.findById(mno);

        result.ifPresent(todoEntity -> {
            System.out.println(todoEntity);
        });
    }

    @Test
    @Transactional
    public void testUpdateDrityCheck() {
        Long mno = 58L;

        Optional<TodoEntity> result = todoRepository.findById(mno);

        TodoEntity todoEntity = result.get();

        System.out.println("OLD : " + todoEntity);

        todoEntity.changeTitle("Change Title..." + Math.random());
        todoEntity.changeWriter("Change Writer..." + Math.random());

        System.out.println("changed : " + todoEntity);
        todoRepository.save(todoEntity);
    }

    @Test
    @Transactional
    @Commit //기본 롤백이기에 결과보고싶으면 설정
    public void testDelete() {
        Long mno = 101L;

        Optional<TodoEntity> result = todoRepository.findById(mno);

        result.ifPresent(todoEntity -> {
            todoRepository.delete(todoEntity);
        });
    }
}