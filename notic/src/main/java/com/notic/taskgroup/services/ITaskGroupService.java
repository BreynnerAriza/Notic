package com.notic.taskgroup.services;

import com.notic.taskgroup.dtos.request.TaskGroupCreateDTO;
import com.notic.taskgroup.dtos.request.TaskGroupUpdateDTO;
import com.notic.taskgroup.dtos.response.TaskGroupResponseDTO;

import java.util.Set;

public interface ITaskGroupService {

    TaskGroupResponseDTO create(TaskGroupCreateDTO taskGroupCreateDTO);
    Set<TaskGroupResponseDTO> getAllByUser();
    TaskGroupResponseDTO update(Integer id, TaskGroupUpdateDTO taskGroupUpdateDTO);
    TaskGroupResponseDTO delete(Integer idGroupTask);

}
