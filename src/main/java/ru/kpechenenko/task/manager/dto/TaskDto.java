package ru.kpechenenko.task.manager.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kpechenenko.task.manager.entity.Task;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskDto {
    private Integer id;
    private String name;
    private String description;
    private LocalDate deadline;
    private Integer tagId;

    public static TaskDto fromEntity(Task task) {
        return new TaskDto(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getDeadLine(),
            task.getTag().getId()
        );
    }
}
