package ru.kpechenenko.task.manager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public final class TagInput {
    private Integer id;
    private String title;
    private List<TaskInput> tasks;
}
