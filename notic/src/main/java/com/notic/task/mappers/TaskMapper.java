package com.notic.task.mappers;

import com.notic.task.domain.Task;
import com.notic.task.dtos.request.TaskCreateDTO;
import com.notic.task.dtos.request.TaskUpdateDTO;
import com.notic.task.dtos.response.TaskResponseDTO;
import com.notic.taskgroup.mappers.TaskGroupMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TaskGroupMapper.class})
public interface TaskMapper {

    Task taskCreateToTask(TaskCreateDTO taskCreateDTO);
    Task taskUpdateToTask(TaskUpdateDTO taskUpdateDTO);
    TaskResponseDTO taskToTaskResponse(Task task);

    List<TaskResponseDTO> taskListToTaskResponseList(List<Task> tasks);


}
