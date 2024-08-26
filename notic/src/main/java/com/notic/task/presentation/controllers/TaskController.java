package com.notic.task.presentation.controllers;

import com.notic.common.dtos.responses.SuccessResponseDTO;
import com.notic.task.facade.dtos.request.TaskCreateDTO;
import com.notic.task.facade.dtos.request.TaskUpdateDTO;
import com.notic.task.facade.dtos.response.TaskResponseDTO;
import com.notic.task.facade.handlers.ITaskHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.core.Ordered;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Validated
public class TaskController {

    private final ITaskHandler taskHandler;

    @GetMapping("/tasks/{task-id}")
    public ResponseEntity<SuccessResponseDTO<TaskResponseDTO>> getById(@PathVariable(name = "task-id") Integer idTask){
        TaskResponseDTO taskResponse = taskHandler.getById(idTask);
        SuccessResponseDTO<TaskResponseDTO> response = new SuccessResponseDTO<>(
                HttpStatus.OK.value(), taskResponse
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/tasks-groups/{task-group-id}/tasks/")
    public ResponseEntity<SuccessResponseDTO<Page<TaskResponseDTO>>> getAllByGroup(
            @PathVariable(name = "task-group-id") Integer idGroup,
            @PageableDefault(page = 0, size = 20, sort = "expirationDate", direction = Sort.Direction.ASC) Pageable pageable
    ){
        Page<TaskResponseDTO> taskResponse = taskHandler.getAllByGroup(idGroup, pageable);
        SuccessResponseDTO<Page<TaskResponseDTO>> response = new SuccessResponseDTO<>(
                HttpStatus.OK.value(), taskResponse
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/tasks-groups/{task-group-id}/tasks/")
    public ResponseEntity<SuccessResponseDTO<TaskResponseDTO>> update(
            @PathVariable(name = "task-group-id") Integer idGroup,
            @Valid @RequestBody TaskCreateDTO taskCreateDTO
    ){
        TaskResponseDTO taskResponse = taskHandler.create(idGroup, taskCreateDTO);
        SuccessResponseDTO<TaskResponseDTO> response = new SuccessResponseDTO<>(
                HttpStatus.CREATED.value(), taskResponse
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/tasks/{task-id}")
    public ResponseEntity<SuccessResponseDTO<TaskResponseDTO>> changeCompleted(@PathVariable(name = "task-id") Integer idTask){
        TaskResponseDTO taskResponse = taskHandler.changeCompleted(idTask);
        SuccessResponseDTO<TaskResponseDTO> response = new SuccessResponseDTO<>(
                HttpStatus.OK.value(), taskResponse
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/tasks/{task-id}")
    public ResponseEntity<SuccessResponseDTO<TaskResponseDTO>> update(
            @PathVariable(name = "task-id") Integer idTask,
            @Valid @RequestBody TaskUpdateDTO taskUpdateDTO
    ){
        TaskResponseDTO taskResponse = taskHandler.update(idTask, taskUpdateDTO);
        SuccessResponseDTO<TaskResponseDTO> response = new SuccessResponseDTO<>(
                HttpStatus.OK.value(), taskResponse
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @DeleteMapping("/tasks/{task-id}")
    public ResponseEntity<SuccessResponseDTO<TaskResponseDTO>> delete(@PathVariable(name = "task-id") Integer idTask){
        TaskResponseDTO taskResponse = taskHandler.delete(idTask);
        SuccessResponseDTO<TaskResponseDTO> response = new SuccessResponseDTO<>(
                HttpStatus.OK.value(), taskResponse
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
