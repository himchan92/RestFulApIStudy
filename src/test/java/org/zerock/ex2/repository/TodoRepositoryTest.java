package org.zerock.ex2.repository;

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
import org.zerock.ex2.dto.TodoDTO;
import org.zerock.ex2.entity.TodoEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest //DB 관련부분만 테스트 수행
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testInsert() {
        TodoEntity todoEntity = TodoEntity.builder()
                //pk mno는 자동생성으로 별도추가 안해도됨
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
    @Transactional //한 트랜젝션 유지로 기존 영속성에 존재시 이후 중복호출 안함
    public void testRead() {
        Long mno = 58L;

        //조회시 영속성컨택스트 1차캐시 우선조회후 있으면 꺼내고 없으면 DB쪽 조회
        Optional<TodoEntity> result = todoRepository.findById(mno);

        result.ifPresent(todoEntity -> {
            System.out.println(todoEntity);
        });
    }

    @Test
    @Transactional
    @Commit //자동 롤백으로 결과보기위해 임시 설정
    public void testUpdateDirtyCheck() {
        Long mno = 58L;

        Optional<TodoEntity> result = todoRepository.findById(mno);
        TodoEntity todoEntity = result.get();

        System.out.println("OLD: " + todoEntity);

        //@Transactional 있으면 별도 save없이 setter 작업하면 변경감지로 JPA가 UPDATE 지원
        todoEntity.changeTitle("Change Title..." + Math.random());
        todoEntity.changeWriter("Change Writer..." + Math.random());

        System.out.println("Changed : " + todoEntity);
    }

    @Test
    @Transactional //변경, 삭제 작업시 JPA 변경감지로 자동 UPDATE, DELETE SQL 수행 지원
    @Commit //본래 바람직하지 않지만 결과확인을 위해 임시 설정
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
        //삭제 전 확인
        Long mno = 100L;
        todoRepository.deleteById(mno); //ID기준 삭제수행으로 delete메소드와 차이없음
    }

    @Test
    public void testPaging() {
        //페이징: 0 ~  10 까지 mno 기준 내림차순
        PageRequest pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

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
        PageRequest pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<TodoEntity> result = todoRepository.listAll(pageable);

        System.out.println(result.getContent());
    }

    @Test
    public void testSearch1() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<TodoEntity> result = todoRepository.search1(pageable);

        System.out.println(result.getTotalPages());

        System.out.println(result.getTotalElements());

        List<TodoEntity> todoEntityList = result.getContent();

        todoEntityList.forEach(todoEntity -> {
            System.out.println(todoEntity);
        });
    }

    @Test
    public void testGetTodoDTO() {
        Long mno = 59L;

        Optional<TodoDTO> result = todoRepository.getDTO(mno);

        result.ifPresent(todoDTO -> {
            System.out.println(todoDTO);
        });
    }

    @Test
    public void testSearchDTO() {

        PageRequest pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<TodoDTO> result = todoRepository.searchDTO(pageable);

        System.out.println(result.getTotalPages());

        System.out.println(result.getTotalElements());

        List<TodoDTO> dtoList = result.getContent();

        dtoList.forEach(todoDTO -> {
            System.out.println(todoDTO);
        });
    }
}