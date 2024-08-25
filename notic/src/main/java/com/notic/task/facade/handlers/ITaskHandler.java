package com.notic.task.facade.handlers;


import com.notic.task.facade.dtos.request.TaskCreateDTO;
import com.notic.task.facade.dtos.request.TaskUpdateDTO;
import com.notic.task.facade.dtos.response.TaskResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITaskHandler {

    TaskResponseDTO getById(Integer idTask);
    Page<TaskResponseDTO> getAllByGroup(Integer idGroup, Pageable pageable);
    TaskResponseDTO create(Integer idGroup, TaskCreateDTO taskCreateDTO);
    TaskResponseDTO changeCompleted(Integer idTask);
    TaskResponseDTO update(Integer idTask, TaskUpdateDTO taskUpdateDTO);
    TaskResponseDTO delete(Integer idTask);

}
