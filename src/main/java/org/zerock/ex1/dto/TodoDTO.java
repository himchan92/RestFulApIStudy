package org.zerock.ex1.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.ex1.entity.TodoEntity;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TodoDTO {

    private Long mno;

    private String title;

    private String writer;

    private LocalDate dueDate;

    public TodoDTO(TodoEntity todoEntity) {
        this.mno = todoEntity.getMno();
        this.title = todoEntity.getTitle();
        this.writer = todoEntity.getWriter();
        this.dueDate = todoEntity.getDueDate();
    }

    public TodoEntity todoEntity() {
        return TodoEntity.builder()
                .mno(mno)
                .title(title)
                .writer(writer)
                .dueDate(dueDate)
                .build();
    }
}
