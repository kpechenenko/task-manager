package ru.kpechenenko.task.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public final class TagDto {
    private Integer id;
    private String name;
    private List<TaskInput> tasks;
}
