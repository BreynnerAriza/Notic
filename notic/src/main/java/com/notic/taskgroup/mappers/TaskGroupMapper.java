package com.notic.taskgroup.mappers;

import com.notic.task.domain.Task;
import com.notic.taskgroup.domain.TaskGroup;
import com.notic.taskgroup.dtos.request.TaskGroupCreateDTO;
import com.notic.taskgroup.dtos.request.TaskGroupUpdateDTO;
import com.notic.taskgroup.dtos.response.TaskGroupResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskGroupMapper {

    TaskGroupResponseDTO taskGroupToTaskGroupResponse(TaskGroup taskGroup);
    TaskGroup taskGroupCreateToTaskGroup(TaskGroupCreateDTO taskGroupCreate);
    TaskGroup taskGroupUpdateToTaskGroup(TaskGroupUpdateDTO taskGroupUpdate);
    TaskGroup taskGroupResponseToTaskGroup(TaskGroupResponseDTO taskGroupResponseDTO);
    List<TaskGroupResponseDTO> taskGroupListToTaskGroupResponseList(List<TaskGroup> taskGroups);

}
