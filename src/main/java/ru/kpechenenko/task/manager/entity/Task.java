package ru.kpechenenko.task.manager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "task")
@Table(name = "tasks")
@NoArgsConstructor
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "deadline", nullable = false)
    private LocalDate deadLine;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

    public Task(String title, String description, LocalDate deadLine, Tag tag) {
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.tag = tag;
    }
}
