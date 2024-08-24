package com.notic.task.business.services.impl;

import com.notic.common.exceptions.customexceptions.AlreadyExistsException;
import com.notic.common.exceptions.customexceptions.NotFoundException;
import com.notic.task.business.services.ITaskService;
import com.notic.task.persistence.entities.Task;
import com.notic.task.persistence.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.notic.task.utils.constants.TaskExceptionConstants.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements ITaskService {

    private final TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public Task getById(Integer idTask) {
        return findTaskById(idTask);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllByGroup(Integer idGroup) {
        return taskRepository.findAllByGroup(idGroup);
    }

    @Override
    @Transactional
    public Task create(Task task) {
        if(taskRepository.findByTitleAndGroup(
                task.getTitle(), task.getTaskGroup().getTaskGroupId()).isPresent()
        ){
            throw new AlreadyExistsException(
                    TITLE_TASK_ALREADY_EXITS.getTitle(), TITLE_TASK_ALREADY_EXITS.getMessage(),
                    TITLE_TASK_ALREADY_EXITS.getStatus()
            );
        }
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task changeCompleted(Integer idTask) {
        Task task = findTaskById(idTask);
        taskRepository.changeCompleted(task.getTaskId());
        task.setCompleted(!task.getCompleted());
        return task;
    }

    @Override
    @Transactional
    public Task update(Task taskUpdate) {
        if(taskRepository.findByTitleAndGroupDistinctId(
                taskUpdate.getTitle(), taskUpdate.getTaskGroup().getTaskGroupId(), taskUpdate.getTaskId()).isPresent()
        ){
            throw new AlreadyExistsException(
                    TITLE_TASK_ALREADY_EXITS.getTitle(), TITLE_TASK_ALREADY_EXITS.getMessage(),
                    TITLE_TASK_ALREADY_EXITS.getStatus()
            );
        }
        return taskRepository.save(taskUpdate);
    }

    @Override
    @Transactional
    public Task delete(Integer idTask) {
        Task task = findTaskById(idTask);
        taskRepository.delete(task);
        return task;
    }

    private Task findTaskById(Integer idTask){
        return taskRepository.findById(idTask).orElseThrow(
                () -> new NotFoundException(
                        TASK_NOT_FOUND.getTitle(), TASK_NOT_FOUND.getMessage(), TASK_NOT_FOUND.getStatus()
                )
        );
    }

}
