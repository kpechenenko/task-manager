package ru.kpechenenko.task.manager.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ResponseModel<T> {
    private Integer code;
    private String message;
    private T data;

    public ResponseModel(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseModel(Integer code, T data) {
        this.code = code;
        this.data = data;
    }
}
