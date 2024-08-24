package com.notic.task.business.services;


import com.notic.task.persistence.entities.Task;

import java.util.List;

public interface ITaskService {

    Task getById(Integer idTask);
    List<Task> getAllByGroup(Integer idGroup);
    Task create(Task task);
    Task changeCompleted(Integer idTask);
    Task update(Task taskUpdate);
    Task delete(Integer idTask);

}
