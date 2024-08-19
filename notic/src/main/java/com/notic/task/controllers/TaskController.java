package com.notic.task.controllers;

import com.notic.common.dtos.responses.SuccessResponseDTO;
import com.notic.task.dtos.request.TaskCreateDTO;
import com.notic.task.dtos.request.TaskUpdateDTO;
import com.notic.task.dtos.response.TaskResponseDTO;
import com.notic.task.services.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TaskController {

    private final ITaskService taskService;

    @GetMapping("/tasks/{task-id}")
    public ResponseEntity<SuccessResponseDTO<TaskResponseDTO>> getById(@PathVariable(name = "task-id") Integer idTask){
        TaskResponseDTO taskResponse = taskService.getById(idTask);
        SuccessResponseDTO<TaskResponseDTO> response = new SuccessResponseDTO<>(
                HttpStatus.OK.value(), taskResponse
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/tasks-groups/{task-group-id}/tasks/")
    public ResponseEntity<SuccessResponseDTO<List<TaskResponseDTO>>> getAllByGroup(
            @PathVariable(name = "task-group-id") Integer idGroup
    ){
        List<TaskResponseDTO> taskResponse = taskService.getAllByGroup(idGroup);
        SuccessResponseDTO<List<TaskResponseDTO>> response = new SuccessResponseDTO<>(
                HttpStatus.OK.value(), taskResponse
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/tasks-groups/{task-group-id}/tasks/")
    public ResponseEntity<SuccessResponseDTO<TaskResponseDTO>> update(
            @PathVariable(name = "task-group-id") Integer idGroup,
            @RequestBody TaskCreateDTO taskCreateDTO
    ){
        TaskResponseDTO taskResponse = taskService.create(idGroup, taskCreateDTO);
        SuccessResponseDTO<TaskResponseDTO> response = new SuccessResponseDTO<>(
                HttpStatus.CREATED.value(), taskResponse
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/tasks/{task-id}")
    public ResponseEntity<SuccessResponseDTO<TaskResponseDTO>> changeCompleted(@PathVariable(name = "task-id") Integer idTask){
        TaskResponseDTO taskResponse = taskService.changeCompleted(idTask);
        SuccessResponseDTO<TaskResponseDTO> response = new SuccessResponseDTO<>(
                HttpStatus.OK.value(), taskResponse
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/tasks/{task-id}")
    public ResponseEntity<SuccessResponseDTO<TaskResponseDTO>> update(
            @PathVariable(name = "task-id") Integer idTask,
            @RequestBody TaskUpdateDTO taskUpdateDTO
            ){
        TaskResponseDTO taskResponse = taskService.update(idTask, taskUpdateDTO);
        SuccessResponseDTO<TaskResponseDTO> response = new SuccessResponseDTO<>(
                HttpStatus.OK.value(), taskResponse
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @DeleteMapping("/tasks/{task-id}")
    public ResponseEntity<SuccessResponseDTO<TaskResponseDTO>> delete(@PathVariable(name = "task-id") Integer idTask){
        TaskResponseDTO taskResponse = taskService.delete(idTask);
        SuccessResponseDTO<TaskResponseDTO> response = new SuccessResponseDTO<>(
                HttpStatus.OK.value(), taskResponse
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
