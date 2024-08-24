package com.notic.task.facade.handlers;


import com.notic.task.facade.dtos.request.TaskCreateDTO;
import com.notic.task.facade.dtos.request.TaskUpdateDTO;
import com.notic.task.facade.dtos.response.TaskResponseDTO;
import com.notic.task.persistence.entities.Task;

import java.util.List;

public interface ITaskHandler {

    TaskResponseDTO getById(Integer idTask);
    List<TaskResponseDTO> getAllByGroup(Integer idGroup);
    TaskResponseDTO create(Integer idGroup, TaskCreateDTO taskCreateDTO);
    TaskResponseDTO changeCompleted(Integer idTask);
    TaskResponseDTO update(Integer idTask, TaskUpdateDTO taskUpdateDTO);
    TaskResponseDTO delete(Integer idTask);

}
