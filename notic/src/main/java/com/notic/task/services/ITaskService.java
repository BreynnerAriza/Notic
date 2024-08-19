package com.notic.task.services;


import com.notic.task.dtos.request.TaskCreateDTO;
import com.notic.task.dtos.request.TaskUpdateDTO;
import com.notic.task.dtos.response.TaskResponseDTO;

import java.util.List;

public interface ITaskService {

    TaskResponseDTO getById(Integer idTask);
    List<TaskResponseDTO> getAllByGroup(Integer idGroup);
    TaskResponseDTO create(Integer idGroup, TaskCreateDTO taskCreateDTO);
    TaskResponseDTO changeCompleted(Integer idTask);
    TaskResponseDTO update(Integer idTask, TaskUpdateDTO taskUpdateDTO);
    TaskResponseDTO delete(Integer idTask);

}
