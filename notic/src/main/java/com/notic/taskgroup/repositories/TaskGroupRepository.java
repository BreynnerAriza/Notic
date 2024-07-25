package com.notic.taskgroup.repositories;

import com.notic.taskgroup.domain.TaskGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TaskGroupRepository extends JpaRepository<TaskGroup, Integer> {

    @Query("""
        SELECT tg FROM TaskGroup tg
        WHERE
        tg.user.userId = :userId
    """)
    Set<TaskGroup> findAllByUserId(@Param("userId") Integer userId);

    @Query("""
        SELECT tg FROM TaskGroup tg
        WHERE
        tg.user.userId = :userId AND tg.name = :name
    """)
    Optional<TaskGroup> findTaskGroupByNameAndUserId(@Param("name") String name,@Param("userId") Integer userId);

}
