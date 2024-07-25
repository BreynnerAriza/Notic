package com.notic.task.repositories;

import com.notic.task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("""
        SELECT t FROM Task t
        WHERE
        t.taskGroup.taskGroupId = :taskGroupId
    """)
    Set<Task> findAllByGroup(@Param("taskGroupId") Integer taskGroupId);

    @Query("""
        SELECT t FROM Task t
        WHERE
        t.taskGroup.taskGroupId = :taskGroupId AND t.title = :title
    """)
    Optional<Task> findByTitleAndGroup(@Param("title") String title, @Param("taskGroupId") Integer taskGroupId);

    @Query("""
        UPDATE Task t SET t.completed = NOT (t.completed)
        WHERE
        t.taskId = :taskId
    """)
    Optional<Task> changeCompleted(@Param("taskId") Integer taskId);

}
