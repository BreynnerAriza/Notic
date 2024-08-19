package com.notic.taskgroup.services;

import com.notic.taskgroup.domain.TaskGroup;
import com.notic.taskgroup.dtos.request.TaskGroupCreateDTO;
import com.notic.taskgroup.dtos.request.TaskGroupUpdateDTO;
import com.notic.taskgroup.dtos.response.TaskGroupResponseDTO;

import java.util.List;

public interface ITaskGroupService {

    TaskGroupResponseDTO create(TaskGroupCreateDTO taskGroupCreateDTO);
    TaskGroupResponseDTO getById(Integer id);
    List<TaskGroupResponseDTO> getAllByUser();
    TaskGroupResponseDTO update(Integer id, TaskGroupUpdateDTO taskGroupUpdateDTO);
    TaskGroupResponseDTO delete(Integer idGroupTask);

}
