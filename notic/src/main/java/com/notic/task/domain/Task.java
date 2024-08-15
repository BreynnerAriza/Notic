package com.notic.task.domain;

import com.notic.taskgroup.domain.TaskGroup;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "TASKS")
@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode
@ToString
public class Task {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Integer taskId;
    private String title;
    private String description;
    private Boolean completed;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Temporal(TemporalType.TIME)
    @Column(name = "expiration_hour")
    private LocalTime expirationHour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_group_id")
    private TaskGroup taskGroup;

}
