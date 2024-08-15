package com.notic.taskgroup.controllers;

import com.notic.common.dtos.responses.SuccessResponseDTO;
import com.notic.taskgroup.dtos.request.TaskGroupCreateDTO;
import com.notic.taskgroup.dtos.request.TaskGroupUpdateDTO;
import com.notic.taskgroup.dtos.response.TaskGroupResponseDTO;
import com.notic.taskgroup.services.ITaskGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/tasks-groups")
@RequiredArgsConstructor
public class TaskGroupController {

    private final ITaskGroupService taskGroupService;

    @GetMapping
    public ResponseEntity<SuccessResponseDTO<Set<TaskGroupResponseDTO>>> getAllByUser(){
        Set<TaskGroupResponseDTO> taskGroups = taskGroupService.getAllByUser();

        SuccessResponseDTO<Set<TaskGroupResponseDTO>> response = new SuccessResponseDTO<>(
                HttpStatus.OK.value(), taskGroups
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping
    public ResponseEntity<SuccessResponseDTO<TaskGroupResponseDTO>> create(
            @RequestBody TaskGroupCreateDTO taskGroupCreateDTO
    ){
        TaskGroupResponseDTO taskGroupResponseDTO = taskGroupService.create(taskGroupCreateDTO);

        SuccessResponseDTO<TaskGroupResponseDTO> response = new SuccessResponseDTO<>(
                HttpStatus.CREATED.value(), taskGroupResponseDTO
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{task-group-id}")
    public ResponseEntity<SuccessResponseDTO<TaskGroupResponseDTO>> patchTaskGroup(
            @PathVariable(name = "task-group-id") Integer idTaskGroup,
            @RequestBody TaskGroupUpdateDTO taskGroupUpdateDTO
    ){
        TaskGroupResponseDTO taskGroupResponseDTO = taskGroupService.update(idTaskGroup, taskGroupUpdateDTO);

        SuccessResponseDTO<TaskGroupResponseDTO> response = new SuccessResponseDTO<>(
                HttpStatus.OK.value(), taskGroupResponseDTO
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @DeleteMapping("/{task-group-id}")
    public ResponseEntity<SuccessResponseDTO<TaskGroupResponseDTO>> delete(
            @PathVariable(name = "task-group-id") Integer idTaskGroup
    ){
        TaskGroupResponseDTO taskGroupRDelete = taskGroupService.delete(idTaskGroup);

        SuccessResponseDTO<TaskGroupResponseDTO> response = new SuccessResponseDTO<>(
                HttpStatus.CREATED.value(), taskGroupRDelete
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
