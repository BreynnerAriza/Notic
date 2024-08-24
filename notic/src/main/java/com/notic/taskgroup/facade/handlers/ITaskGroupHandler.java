package com.notic.taskgroup.facade.handlers;

import com.notic.taskgroup.facade.dtos.request.TaskGroupCreateDTO;
import com.notic.taskgroup.facade.dtos.request.TaskGroupUpdateDTO;
import com.notic.taskgroup.facade.dtos.response.TaskGroupResponseDTO;

import java.util.List;

public interface ITaskGroupHandler {

    TaskGroupResponseDTO create(TaskGroupCreateDTO taskGroupCreateDTO);
    TaskGroupResponseDTO getById(Integer id);
    List<TaskGroupResponseDTO> getAllByUser();
    TaskGroupResponseDTO update(Integer idTaskGroup, TaskGroupUpdateDTO taskGroupUpdateDTO);
    TaskGroupResponseDTO delete(Integer idTaskGroup);

}
