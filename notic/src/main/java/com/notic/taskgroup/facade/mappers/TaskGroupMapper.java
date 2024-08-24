package com.notic.taskgroup.facade.mappers;

import com.notic.taskgroup.persistence.entities.TaskGroup;
import com.notic.taskgroup.facade.dtos.request.TaskGroupCreateDTO;
import com.notic.taskgroup.facade.dtos.request.TaskGroupUpdateDTO;
import com.notic.taskgroup.facade.dtos.response.TaskGroupResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface  TaskGroupMapper {

    TaskGroupResponseDTO taskGroupToTaskGroupResponse(TaskGroup taskGroup);
    TaskGroup taskGroupCreateToTaskGroup(TaskGroupCreateDTO taskGroupCreate);
    default TaskGroup taskGroupUpdateToTaskGroupNew(TaskGroup taskGroupOld, TaskGroupUpdateDTO taskGroupUpdate){
        if(taskGroupUpdate.name() != null) taskGroupOld.setName(taskGroupUpdate.name());
        if(taskGroupUpdate.description() != null) taskGroupOld.setDescription(taskGroupUpdate.description());
        if(taskGroupUpdate.colorIdentifier() != null) taskGroupOld.setColorIdentifier(taskGroupUpdate.colorIdentifier());
        return taskGroupOld;
    }
    TaskGroup taskGroupResponseToTaskGroup(TaskGroupResponseDTO taskGroupResponseDTO);
    List<TaskGroupResponseDTO> taskGroupListToTaskGroupResponseList(List<TaskGroup> taskGroups);

}
