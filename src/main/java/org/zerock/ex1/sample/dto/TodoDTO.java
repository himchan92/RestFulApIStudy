package org.zerock.ex1.sample.dto;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.ex1.sample.entity.TodoEntity;

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
}
