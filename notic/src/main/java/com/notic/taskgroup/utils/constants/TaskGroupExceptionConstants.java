package com.notic.taskgroup.utils.constants;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum TaskGroupExceptionConstants {

    NAME_TASK_GROUP_ALREADY_EXITS("Group name in use", "There is already a group registered with this name", HttpStatus.CONFLICT),
    TASK_GROUP_NOT_FOUND("Task group not found","Task force not registered", HttpStatus.BAD_REQUEST);

    private final String title;
    private final String message;
    private final HttpStatus status;

    TaskGroupExceptionConstants(String title, String message, HttpStatus status) {
        this.title = title;
        this.message = message;
        this.status = status;
    }

}
