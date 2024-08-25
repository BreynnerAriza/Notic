package com.notic.taskgroup.facade.handlers;

import com.notic.taskgroup.facade.dtos.request.TaskGroupCreateDTO;
import com.notic.taskgroup.facade.dtos.request.TaskGroupUpdateDTO;
import com.notic.taskgroup.facade.dtos.response.TaskGroupResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITaskGroupHandler {

    TaskGroupResponseDTO create(TaskGroupCreateDTO taskGroupCreateDTO);
    TaskGroupResponseDTO getById(Integer id);
    Page<TaskGroupResponseDTO> getAllByUser(Pageable pageable);
    TaskGroupResponseDTO update(Integer idTaskGroup, TaskGroupUpdateDTO taskGroupUpdateDTO);
    TaskGroupResponseDTO delete(Integer idTaskGroup);

}
