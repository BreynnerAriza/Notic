package com.notic.taskgroup.facade.handlers.impl;

import com.notic.auth.facade.handlers.IAuthenticationHandler;
import com.notic.taskgroup.business.services.ITaskGroupService;
import com.notic.taskgroup.facade.dtos.request.TaskGroupCreateDTO;
import com.notic.taskgroup.facade.dtos.request.TaskGroupUpdateDTO;
import com.notic.taskgroup.facade.dtos.response.TaskGroupResponseDTO;
import com.notic.taskgroup.facade.handlers.ITaskGroupHandler;
import com.notic.taskgroup.facade.mappers.TaskGroupMapper;
import com.notic.taskgroup.persistence.entities.TaskGroup;
import com.notic.user.persistence.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskGroupHandler implements ITaskGroupHandler {

    private final ITaskGroupService taskGroupService;
    private final IAuthenticationHandler authenticationHandler;
    private final TaskGroupMapper taskGroupMapper;

    @Override
    public TaskGroupResponseDTO create(TaskGroupCreateDTO taskGroupCreateDTO) {
        User userAuthenticate = authenticationHandler.getUserAuthenticate();
        TaskGroup taskGroup = taskGroupMapper.taskGroupCreateToTaskGroup(taskGroupCreateDTO);
        taskGroup.setUser(userAuthenticate);
        return taskGroupMapper.taskGroupToTaskGroupResponse(taskGroupService.create(taskGroup));
    }

    @Override
    public TaskGroupResponseDTO getById(Integer id) {
        return taskGroupMapper.taskGroupToTaskGroupResponse(taskGroupService.getById(id));
    }

    @Override
    public Page<TaskGroupResponseDTO> getAllByUser(Pageable pageable) {
        User userAuthenticate = authenticationHandler.getUserAuthenticate();
        Page<TaskGroup> taskGroupsPage = taskGroupService.getAllByUser(userAuthenticate.getUserId(), pageable);
        List<TaskGroup> taskGroupList = taskGroupsPage.getContent();
        List<TaskGroupResponseDTO> taskGroupResponseDTOList = taskGroupMapper.taskGroupListToTaskGroupResponseList(taskGroupList);

        return new PageImpl<>(taskGroupResponseDTOList, pageable, taskGroupsPage.getTotalElements());
    }

    @Override
    public TaskGroupResponseDTO update(Integer idTaskGroup, TaskGroupUpdateDTO taskGroupUpdateDTO) {
        User userAuthenticate = authenticationHandler.getUserAuthenticate();
        TaskGroup taskGroupOld = taskGroupService.getById(idTaskGroup);
        TaskGroup taskGroupNew = taskGroupMapper.taskGroupUpdateToTaskGroupNew(taskGroupOld, taskGroupUpdateDTO);
        taskGroupNew.setUser(userAuthenticate);
        return taskGroupMapper.taskGroupToTaskGroupResponse(taskGroupService.update(taskGroupNew));
    }

    @Override
    public TaskGroupResponseDTO delete(Integer idTaskGroup) {
        return taskGroupMapper.taskGroupToTaskGroupResponse(taskGroupService.delete(idTaskGroup));
    }
}
