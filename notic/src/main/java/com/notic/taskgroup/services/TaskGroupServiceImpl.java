package com.notic.taskgroup.services;

import com.notic.auth.services.IAuthenticationService;
import com.notic.common.exceptions.customexceptions.AlreadyExistsException;
import com.notic.common.exceptions.customexceptions.NotFoundException;
import com.notic.taskgroup.domain.TaskGroup;
import com.notic.taskgroup.dtos.request.TaskGroupCreateDTO;
import com.notic.taskgroup.dtos.request.TaskGroupUpdateDTO;
import com.notic.taskgroup.dtos.response.TaskGroupResponseDTO;
import com.notic.taskgroup.mappers.TaskGroupMapper;
import com.notic.taskgroup.repositories.TaskGroupRepository;
import com.notic.user.domain.User;
import com.notic.user.dtos.response.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.notic.taskgroup.constants.TaskGroupExceptionConstants.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskGroupServiceImpl implements ITaskGroupService{

    private final IAuthenticationService authenticationService;
    private final TaskGroupMapper taskGroupMapper;
    private final TaskGroupRepository taskGroupRepository;

    @Override
    @Transactional
    public TaskGroupResponseDTO create(TaskGroupCreateDTO taskGroupCreateDTO) {
        UserResponseDTO userAuthenticate = authenticationService.getUserAuthenticate();
        if(taskGroupRepository.findTaskGroupByNameAndUserId(
                taskGroupCreateDTO.name(), userAuthenticate.userId()).isPresent()
        ){
            throw new AlreadyExistsException(
                    NAME_TASK_GROUP_ALREADY_EXITS.getTitle(), NAME_TASK_GROUP_ALREADY_EXITS.getMessage(),
                    NAME_TASK_GROUP_ALREADY_EXITS.getStatus()
            );
        }

        TaskGroup taskGroup = taskGroupMapper.taskGroupCreateToTaskGroup(taskGroupCreateDTO);
        taskGroup.setUser(User.builder().userId(userAuthenticate.userId()).build());
        TaskGroup taskGroupCreated = taskGroupRepository.save(taskGroup);

        return taskGroupMapper.taskGroupToTaskGroupResponse(taskGroupCreated);
    }

    @Override
    public TaskGroupResponseDTO getById(Integer id) {
        TaskGroup taskGroup = findTaskGroupById(id);
        return taskGroupMapper.taskGroupToTaskGroupResponse(taskGroup);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskGroupResponseDTO> getAllByUser() {
        UserResponseDTO userAuthenticate = authenticationService.getUserAuthenticate();
        List<TaskGroup> taskGroupByUser = taskGroupRepository.findAllByUserId(userAuthenticate.userId());
        return taskGroupMapper.taskGroupListToTaskGroupResponseList(taskGroupByUser);
    }

    @Override
    @Transactional
    public TaskGroupResponseDTO update(Integer id, TaskGroupUpdateDTO taskGroupUpdateDTO) {
        UserResponseDTO userAuthenticate = authenticationService.getUserAuthenticate();
        TaskGroup taskGroupBd = findTaskGroupById(id);
        if(taskGroupRepository.findTaskGroupByNameAndDistinctId(
                taskGroupUpdateDTO.name(), id, userAuthenticate.userId()).isPresent()
        ){
            throw new AlreadyExistsException(
                    NAME_TASK_GROUP_ALREADY_EXITS.getTitle(), NAME_TASK_GROUP_ALREADY_EXITS.getMessage(),
                    NAME_TASK_GROUP_ALREADY_EXITS.getStatus()
            );
        }
        if(taskGroupUpdateDTO.name() != null) taskGroupBd.setName(taskGroupUpdateDTO.name());
        if(taskGroupUpdateDTO.description() != null) taskGroupBd.setDescription(taskGroupUpdateDTO.description());
        if(taskGroupUpdateDTO.colorIdentifier() != null) taskGroupBd.setColorIdentifier(taskGroupUpdateDTO.colorIdentifier());

        TaskGroup taskGroupUpdate = taskGroupRepository.save(taskGroupBd);

        return taskGroupMapper.taskGroupToTaskGroupResponse(taskGroupUpdate);
    }

    @Override
    @Transactional
    public TaskGroupResponseDTO delete(Integer idGroupTask) {
        TaskGroup taskDelete = findTaskGroupById(idGroupTask);
        taskGroupRepository.delete(taskDelete);
        return taskGroupMapper.taskGroupToTaskGroupResponse(taskDelete);
    }


    private TaskGroup findTaskGroupById(Integer id){
        return taskGroupRepository.findById(id).orElseThrow(
                () -> new NotFoundException(
                        TASK_GROUP_NOT_FOUND.getTitle(),
                        TASK_GROUP_NOT_FOUND.getMessage(),
                        TASK_GROUP_NOT_FOUND.getStatus()
                )
        );
    }

}
