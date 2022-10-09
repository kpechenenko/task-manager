package ru.kpechenenko.task.manager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class TaskInput {
    private Integer id;
    private String name;
    private String description;
    private LocalDate deadline;
    private Integer tagId;
}
