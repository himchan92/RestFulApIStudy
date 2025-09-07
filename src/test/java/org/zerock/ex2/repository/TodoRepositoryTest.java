package org.zerock.ex2.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex2.entity.TodoEntity;

import java.time.LocalDate;
import java.util.Optional;

@Log4j2
@DataJpaTest //전체아닌 Entity DB관련부분만 수행
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    @Transactional
    public void testInsert() {
        TodoEntity todoEntity = TodoEntity.builder()
                .title("부트 끝내기")
                .writer("user00")
                .dueDate(LocalDate.of(2025, 12,23))
                .build();

        todoRepository.save(todoEntity);

        log.info("New Entity MNO: " + todoEntity.getMno());
    }

    @Test
    @Transactional
    public void testInsertDummies() {
        for(int i = 0; i < 100; i++) {
            TodoEntity todoEntity = TodoEntity.builder()
                    .title("test todo..." + i)
                    .writer("tester" + i)
                    .dueDate(LocalDate.of(2025, 11, 30))
                    .build();

            todoRepository.save(todoEntity);
            log.info("New TodoEntity MNO: " + todoEntity.getMno());
        }
    }

    @Test
    @Transactional(readOnly = true)
    public void testRead() {
        Long mno = 58L;

        Optional<TodoEntity> result = todoRepository.findById(mno);

        result.ifPresent(todoEntity -> {
            log.info(todoEntity);
        });
    }

    @Test
    @Transactional(readOnly = true)
    public void testRead2() {
        Long mno = 55L;

        Optional<TodoEntity> result = todoRepository.findById(mno);

        result.ifPresent(todoEntity -> {
            log.info(todoEntity);
        });

        Optional<TodoEntity> result2 = todoRepository.findById(mno);
        result2.ifPresent(todoEntity -> {
            log.info(todoEntity);
        });
    }

    @Test
    @Transactional
    @Commit //결과보기위한 임시사용
    public void testUpdateDirtyCheck() {
        Long mno = 58L;

        Optional<TodoEntity> result = todoRepository.findById(mno);

        TodoEntity todoEntity = result.get();
        log.info("OLD : " + todoEntity);

        //변경감지 JPA UPDATE 수행지원
        //Transactional 있으면 별도 save 없이도 update 수행
        todoEntity.changeTitle("Change Title..." + Math.random());
        todoEntity.changeWriter("Change Writer..." + Math.random());

        log.info("Changed : " + todoEntity);
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
    @Transactional
    @Commit
    public void testDeleteById() {
        Long mno = 100L;

        todoRepository.deleteById(mno); //ID건 삭제 지원
    }

    @Test
    public void testListAll() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<TodoEntity> result = todoRepository.findAll(pageable);

        log.info(result.getContent());
    }

    @Test
    public void testSearch1() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<TodoEntity> result = todoRepository.search1(pageable);
    }
}