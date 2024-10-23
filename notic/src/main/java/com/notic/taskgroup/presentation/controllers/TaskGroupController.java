package com.notic.taskgroup.presentation.controllers;

import com.notic.common.dtos.responses.SuccessResponseDTO;
import com.notic.taskgroup.facade.dtos.request.TaskGroupCreateDTO;
import com.notic.taskgroup.facade.dtos.request.TaskGroupUpdateDTO;
import com.notic.taskgroup.facade.dtos.response.TaskGroupResponseDTO;
import com.notic.taskgroup.facade.handlers.ITaskGroupHandler;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks-groups")
@RequiredArgsConstructor
@Validated
public class TaskGroupController {

    private final ITaskGroupHandler taskGroupHandler;


    @GetMapping("/{task-group-id}")
    public ResponseEntity<SuccessResponseDTO<TaskGroupResponseDTO>> getById(
            @PathVariable(name = "task-group-id") Integer idTaskGroup
    ){
        TaskGroupResponseDTO taskGroupResponseDTO = taskGroupHandler.getById(idTaskGroup);

        SuccessResponseDTO<TaskGroupResponseDTO> response = new SuccessResponseDTO<>(
                HttpStatus.OK.value(), taskGroupResponseDTO
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/")
    public ResponseEntity<SuccessResponseDTO<Page<TaskGroupResponseDTO>>> getAllByUser(
           @PageableDefault(page = 0, size = 20, sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ){
        Page<TaskGroupResponseDTO> taskGroups = taskGroupHandler.getAllByUser(pageable);

        SuccessResponseDTO<Page<TaskGroupResponseDTO>> response = new SuccessResponseDTO<>(
                HttpStatus.OK.value(), taskGroups
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/")
    public ResponseEntity<SuccessResponseDTO<TaskGroupResponseDTO>> create(
            @Valid @RequestBody TaskGroupCreateDTO taskGroupCreateDTO
    ){
        TaskGroupResponseDTO taskGroupResponseDTO = taskGroupHandler.create(taskGroupCreateDTO);

        SuccessResponseDTO<TaskGroupResponseDTO> response = new SuccessResponseDTO<>(
                HttpStatus.CREATED.value(), taskGroupResponseDTO
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{task-group-id}")
    public ResponseEntity<SuccessResponseDTO<TaskGroupResponseDTO>> patchTaskGroup(
            @PathVariable(name = "task-group-id") Integer idTaskGroup,
            @Valid @RequestBody TaskGroupUpdateDTO taskGroupUpdateDTO
    ){
        TaskGroupResponseDTO taskGroupResponseDTO = taskGroupHandler.update(idTaskGroup, taskGroupUpdateDTO);

        SuccessResponseDTO<TaskGroupResponseDTO> response = new SuccessResponseDTO<>(
                HttpStatus.OK.value(), taskGroupResponseDTO
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @DeleteMapping("/{task-group-id}")
    public ResponseEntity<SuccessResponseDTO<TaskGroupResponseDTO>> delete(
            @PathVariable(name = "task-group-id") Integer idTaskGroup
    ) {
        TaskGroupResponseDTO taskGroupRDelete = taskGroupHandler.delete(idTaskGroup);

        SuccessResponseDTO<TaskGroupResponseDTO> response = new SuccessResponseDTO<>(
                HttpStatus.CREATED.value(), taskGroupRDelete
        );

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }


}
