package com.notic.task.facade.handlers.impl;

import com.notic.task.business.services.ITaskService;
import com.notic.task.facade.dtos.request.TaskCreateDTO;
import com.notic.task.facade.dtos.request.TaskUpdateDTO;
import com.notic.task.facade.dtos.response.TaskResponseDTO;
import com.notic.task.facade.handlers.ITaskHandler;
import com.notic.task.facade.mappers.TaskMapper;
import com.notic.task.persistence.entities.Task;
import com.notic.taskgroup.business.services.ITaskGroupService;
import com.notic.taskgroup.persistence.entities.TaskGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskHandlerImpl implements ITaskHandler {

    private final ITaskGroupService taskGroupService;
    private final ITaskService taskService;
    private final TaskMapper taskMapper;

    @Override
    public TaskResponseDTO getById(Integer idTask) {
        return taskMapper.taskToTaskResponse(taskService.getById(idTask));
    }

    @Override
    public List<TaskResponseDTO> getAllByGroup(Integer idGroup) {
        taskGroupService.getById(idGroup); //Validate exists
        return taskMapper.taskListToTaskResponseList(taskService.getAllByGroup(idGroup));
    }

    @Override
    public TaskResponseDTO create(Integer idGroup, TaskCreateDTO taskCreateDTO) {
        TaskGroup taskGroup = taskGroupService.getById(idGroup);
        Task task = taskMapper.taskCreateToTask(taskCreateDTO);
        task.setTaskGroup(taskGroup);
        return taskMapper.taskToTaskResponse(taskService.create(task));
    }

    @Override
    public TaskResponseDTO changeCompleted(Integer idTask) {
        return taskMapper.taskToTaskResponse(taskService.changeCompleted(idTask));
    }

    @Override
    public TaskResponseDTO update(Integer idTask, TaskUpdateDTO taskUpdateDTO) {
        Task taskOld = taskService.getById(idTask);
        Task taskNew = taskMapper.taskUpdateToTaskNew(taskOld, taskUpdateDTO);
        taskNew.setTaskGroup(taskGroupService.getById(taskNew.getTaskGroup().getTaskGroupId()));
        return taskMapper.taskToTaskResponse(taskService.update(taskNew));
    }

    @Override
    public TaskResponseDTO delete(Integer idTask) {
        return taskMapper.taskToTaskResponse(taskService.delete(idTask));
    }


}
