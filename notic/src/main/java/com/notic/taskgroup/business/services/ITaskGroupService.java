package com.notic.taskgroup.business.services;

import com.notic.taskgroup.persistence.entities.TaskGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITaskGroupService {

    TaskGroup create(TaskGroup taskGroup);
    TaskGroup getById(Integer id);
    Page<TaskGroup> getAllByUser(Integer idUser, Pageable pageable);
    TaskGroup update(TaskGroup taskGroup);
    TaskGroup delete(Integer idGroupTask);

}
