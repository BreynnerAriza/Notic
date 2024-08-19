package com.notic.task.services;

import com.notic.common.exceptions.customexceptions.AlreadyExistsException;
import com.notic.common.exceptions.customexceptions.NotFoundException;
import com.notic.task.domain.Task;
import com.notic.task.dtos.request.TaskCreateDTO;
import com.notic.task.dtos.request.TaskUpdateDTO;
import com.notic.task.dtos.response.TaskResponseDTO;
import com.notic.task.mappers.TaskMapper;
import com.notic.task.repositories.TaskRepository;
import com.notic.taskgroup.dtos.response.TaskGroupResponseDTO;
import com.notic.taskgroup.mappers.TaskGroupMapper;
import com.notic.taskgroup.services.ITaskGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static  com.notic.task.constants.TaskExceptionConstants.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements ITaskService{

    private final ITaskGroupService taskGroupService;
    private final TaskMapper taskMapper;
    private final TaskGroupMapper taskGroupMapper;
    private final TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public TaskResponseDTO getById(Integer idTask) {
        return taskMapper.taskToTaskResponse(findTaskById(idTask));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponseDTO> getAllByGroup(Integer idGroup) {
        taskGroupService.getById(idGroup);
        return taskMapper.taskListToTaskResponseList(taskRepository.findAllByGroup(idGroup));
    }

    @Override
    @Transactional
    public TaskResponseDTO create(Integer idGroup, TaskCreateDTO taskCreateDTO) {
        TaskGroupResponseDTO taskGroup = taskGroupService.getById(idGroup);
        if(taskRepository.findByTitleAndGroup(
                taskCreateDTO.title(), taskGroup.taskGroupId()).isPresent()
        ){
            throw new AlreadyExistsException(
                    TITLE_TASK_ALREADY_EXITS.getTitle(), TITLE_TASK_ALREADY_EXITS.getMessage(),
                    TITLE_TASK_ALREADY_EXITS.getStatus()
            );
        }
         
        Task task = taskMapper.taskCreateToTask(taskCreateDTO);
        task.setTaskGroup(taskGroupMapper.taskGroupResponseToTaskGroup(taskGroup));
        return taskMapper.taskToTaskResponse(taskRepository.save(task));
    }

    @Override
    @Transactional
    public TaskResponseDTO changeCompleted(Integer idTask) {
        Task task = findTaskById(idTask);
        taskRepository.changeCompleted(task.getTaskId());
        task.setCompleted(!task.getCompleted());
        return taskMapper.taskToTaskResponse(task);
    }

    @Override
    @Transactional
    public TaskResponseDTO update(Integer idTask, TaskUpdateDTO taskUpdateDTO) {
        Task task = findTaskById(idTask);
        if(taskUpdateDTO.taskGroupId() != null){
            TaskGroupResponseDTO taskGroup = taskGroupService.getById(taskUpdateDTO.taskGroupId());
            task.setTaskGroup(taskGroupMapper.taskGroupResponseToTaskGroup(taskGroup));
        }
        if(taskRepository.findByTitleAndGroupDistinctId(
                taskUpdateDTO.title(), task.getTaskGroup().getTaskGroupId(), task.getTaskId()).isPresent()
        ){
            throw new AlreadyExistsException(
                    TITLE_TASK_ALREADY_EXITS.getTitle(), TITLE_TASK_ALREADY_EXITS.getMessage(),
                    TITLE_TASK_ALREADY_EXITS.getStatus()
            );
        }

        if(taskUpdateDTO.title() != null) task.setTitle(taskUpdateDTO.title());
        if(taskUpdateDTO.description() != null) task.setDescription(taskUpdateDTO.description());
        if(taskUpdateDTO.completed() != null) task.setCompleted(taskUpdateDTO.completed());
        if(taskUpdateDTO.expirationDate() != null) task.setExpirationDate(taskUpdateDTO.expirationDate());
        if(taskUpdateDTO.expirationHour() != null) task.setExpirationHour(taskUpdateDTO.expirationHour());

        return taskMapper.taskToTaskResponse(taskRepository.save(task));
    }

    @Override
    @Transactional
    public TaskResponseDTO delete(Integer idTask) {
        Task task = findTaskById(idTask);
        taskRepository.delete(task);
        return taskMapper.taskToTaskResponse(task);
    }

    private Task findTaskById(Integer idTask){
        return taskRepository.findById(idTask).orElseThrow(
                () -> new NotFoundException(
                        TASK_NOT_FOUND.getTitle(), TASK_NOT_FOUND.getMessage(), TASK_NOT_FOUND.getStatus()
                )
        );
    }
}
