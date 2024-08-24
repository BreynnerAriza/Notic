package com.notic.taskgroup.business.services;

import com.notic.taskgroup.persistence.entities.TaskGroup;

import java.util.List;

public interface ITaskGroupService {

    TaskGroup create(TaskGroup taskGroup);
    TaskGroup getById(Integer id);
    List<TaskGroup> getAllByUser(Integer idUser);
    TaskGroup update(TaskGroup taskGroup);
    TaskGroup delete(Integer idGroupTask);

}
