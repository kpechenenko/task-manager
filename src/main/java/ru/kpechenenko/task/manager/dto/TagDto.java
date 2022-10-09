package ru.kpechenenko.task.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kpechenenko.task.manager.entity.Tag;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public final class TagDto {
    private Integer id;
    private String name;
    private List<TaskInput> tasks;

    public static TagDto fromEntity(Tag tag) {
        return new TagDto(
            tag.getId(),
            tag.getName(),
            tag.getTasks().stream()
                .map(TaskInput::fromEntity)
                .toList()
        );
    }
}
