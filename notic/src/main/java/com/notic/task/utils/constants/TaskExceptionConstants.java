package com.notic.task.utils.constants;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum TaskExceptionConstants {

    TITLE_TASK_ALREADY_EXITS("Title in use", "There is already a task registered with this title", HttpStatus.CONFLICT),
    TASK_NOT_FOUND("Task not found","Task not registered", HttpStatus.BAD_REQUEST);

    private final String title;
    private final String message;
    private final HttpStatus status;

    TaskExceptionConstants(String title, String message, HttpStatus status) {
        this.title = title;
        this.message = message;
        this.status = status;
    }

}
