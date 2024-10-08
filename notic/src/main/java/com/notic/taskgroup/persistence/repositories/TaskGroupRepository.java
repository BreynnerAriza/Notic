package com.notic.taskgroup.persistence.repositories;

import com.notic.taskgroup.persistence.entities.TaskGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskGroupRepository extends JpaRepository<TaskGroup, Integer> {

    @Query("""
        SELECT tg FROM TaskGroup tg
        WHERE
        tg.user.userId = :userId
    """)
    Page<TaskGroup> findAllByUserId(@Param("userId") Integer userId, Pageable pageable);

    @Query("""
        SELECT tg FROM TaskGroup tg
        WHERE
        tg.user.userId = :userId AND tg.name = :name
    """)
    Optional<TaskGroup> findTaskGroupByNameAndUserId(@Param("name") String name,@Param("userId") Integer userId);

    @Query("""
        SELECT tg FROM TaskGroup  tg
        WHERE
        tg.user.userId = :userId AND tg.name = :name AND NOT tg.taskGroupId = :taskGroupId
    """)
    Optional<TaskGroup> findTaskGroupByNameAndDistinctId(String name, Integer taskGroupId, Integer userId);

}
