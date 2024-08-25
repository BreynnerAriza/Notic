package com.notic.taskgroup.business.services.impl;

import com.notic.common.exceptions.customexceptions.AlreadyExistsException;
import com.notic.common.exceptions.customexceptions.NotFoundException;
import com.notic.taskgroup.business.services.ITaskGroupService;
import com.notic.taskgroup.persistence.entities.TaskGroup;
import com.notic.taskgroup.persistence.repositories.TaskGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.notic.taskgroup.utils.constants.TaskGroupExceptionConstants.*;

@Service
@RequiredArgsConstructor
public class TaskGroupServiceImpl implements ITaskGroupService {

    private final TaskGroupRepository taskGroupRepository;

    @Override
    @Transactional
    public TaskGroup create(TaskGroup taskGroup) {
        if(taskGroupRepository.findTaskGroupByNameAndUserId(
                taskGroup.getName(), taskGroup.getUser().getUserId()).isPresent()
        ){
            throw new AlreadyExistsException(
                    NAME_TASK_GROUP_ALREADY_EXITS.getTitle(), NAME_TASK_GROUP_ALREADY_EXITS.getMessage(),
                    NAME_TASK_GROUP_ALREADY_EXITS.getStatus()
            );
        }
        return taskGroupRepository.save(taskGroup);
    }

    @Override
    public TaskGroup getById(Integer id) {
        return findTaskGroupById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaskGroup> getAllByUser(Integer idUser, Pageable pageable) {
        return taskGroupRepository.findAllByUserId(idUser, pageable);
    }

    @Override
    @Transactional
    public TaskGroup update(TaskGroup taskGroup) {
        if(taskGroupRepository.findTaskGroupByNameAndDistinctId(
                taskGroup.getName(), taskGroup.getTaskGroupId(), taskGroup.getUser().getUserId()).isPresent()
        ){
            throw new AlreadyExistsException(
                    NAME_TASK_GROUP_ALREADY_EXITS.getTitle(), NAME_TASK_GROUP_ALREADY_EXITS.getMessage(),
                    NAME_TASK_GROUP_ALREADY_EXITS.getStatus()
            );
        }
        return taskGroupRepository.save(taskGroup);
    }

    @Override
    @Transactional
    public TaskGroup delete(Integer idGroupTask) {
        TaskGroup taskDelete = findTaskGroupById(idGroupTask);
        taskGroupRepository.delete(taskDelete);
        return taskDelete;
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
