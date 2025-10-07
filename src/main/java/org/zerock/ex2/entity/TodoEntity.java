package org.zerock.ex2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_todos")
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //mariadb auto increment
    private Long mno;

    @Column(length = 500, nullable = false) //length 지정안하면 기본 255
    private String title;

    @Column(length = 500, nullable = false)
    private String writer;

    private LocalDate dueDate;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeWriter(String writer) {
        this.writer = writer;
    }

    public void changeDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
