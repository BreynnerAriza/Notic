package com.notic.task.business.services;


import com.notic.task.persistence.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITaskService {

    Task getById(Integer idTask);
    Page<Task> getAllByGroup(Integer idGroup, Integer idUser, Pageable pageable);
    Task create(Task task);
    Task changeCompleted(Integer idTask);
    Task update(Task taskUpdate);
    Task delete(Integer idTask);

}
