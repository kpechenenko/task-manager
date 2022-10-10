package ru.kpechenenko.task.manager.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public final class TagDto {
    private Integer id;
    private String name;
    private List<TaskDto> tasks;

    public static TagDto fromEntity(Tag tag) {
        return new TagDto(
            tag.getId(),
            tag.getName(),
            tag.getTasks().stream()
                .map(TaskDto::fromEntity)
                .toList()
        );
    }
}
