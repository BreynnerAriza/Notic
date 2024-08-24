package com.notic.task.facade.mappers;

import com.notic.task.persistence.entities.Task;
import com.notic.task.facade.dtos.request.TaskCreateDTO;
import com.notic.task.facade.dtos.request.TaskUpdateDTO;
import com.notic.task.facade.dtos.response.TaskResponseDTO;
import com.notic.taskgroup.facade.mappers.TaskGroupMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {TaskGroupMapper.class})
public interface TaskMapper {

    Task taskCreateToTask(TaskCreateDTO taskCreateDTO);
    default Task taskUpdateToTaskNew(Task taskOld, TaskUpdateDTO taskUpdateDTO){
        if(taskUpdateDTO.title() != null) taskOld.setTitle(taskUpdateDTO.title());
        if(taskUpdateDTO.description() != null) taskOld.setDescription(taskUpdateDTO.description());
        if(taskUpdateDTO.completed() != null) taskOld.setCompleted(taskUpdateDTO.completed());
        if(taskUpdateDTO.expirationDate() != null) taskOld.setExpirationDate(taskUpdateDTO.expirationDate());
        if(taskUpdateDTO.expirationHour() != null) taskOld.setExpirationHour(taskUpdateDTO.expirationHour());
        return taskOld;
    }
    TaskResponseDTO taskToTaskResponse(Task task);

    List<TaskResponseDTO> taskListToTaskResponseList(List<Task> tasks);


}
