package com.notic.task.persistence.repositories;

import com.notic.task.persistence.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("""
        SELECT t FROM Task t
        WHERE
        t.taskGroup.taskGroupId = :taskGroupId
    """)
    List<Task> findAllByGroup(@Param("taskGroupId") Integer taskGroupId);

    @Query("""
        SELECT t FROM Task t
        WHERE
        t.taskGroup.taskGroupId = :taskGroupId AND t.title = :title
    """)
    Optional<Task> findByTitleAndGroup(@Param("title") String title, @Param("taskGroupId") Integer taskGroupId);

    @Modifying
    @Query("""
        UPDATE Task t SET t.completed =
        (CASE
            WHEN t.completed = TRUE THEN FALSE
            ELSE TRUE
        END)
        WHERE
        t.taskId = :taskId
    """)
    void changeCompleted(@Param("taskId") Integer taskId);

    @Query("""
        SELECT t FROM Task t
        WHERE
        t.taskGroup.taskGroupId = :taskGroupId AND t.title = :title AND NOT t.taskId = :taskId
    """)
    Optional<Task> findByTitleAndGroupDistinctId(@Param("title") String title, @Param("taskGroupId") Integer taskGroupId, Integer taskId);

}
