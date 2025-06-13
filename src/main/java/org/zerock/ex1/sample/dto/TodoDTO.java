package org.zerock.ex1.sample.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.ex1.sample.entity.TodoEntity;

@Data
@NoArgsConstructor
public class TodoDTO {

    private Long mno;

    @NotEmpty
    private String title;

    @NotEmpty
    private String writer;

    @FutureOrPresent //현재혹은 미래날짜만 셋팅
    private LocalDate dueDate;

    public TodoDTO(TodoEntity todoEntity) {
        this.mno = todoEntity.getMno();
        this.title = todoEntity.getTitle();
        this.writer = todoEntity.getWriter();
        this.dueDate = todoEntity.getDueDate();
    }

    //DTO <-> Entity
    public TodoEntity toEntity() {
        return TodoEntity.builder()
            .mno(mno)
            .title(title)
            .writer(writer)
            .dueDate(dueDate)
            .build();
    }
}
