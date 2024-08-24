package com.notic.taskgroup.persistence.entities;

import com.notic.task.persistence.entities.Task;
import com.notic.user.persistence.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "TASKS_GROUPS")
@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode
@ToString
public class TaskGroup {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_group_id")
    private Integer taskGroupId;
    private String name;
    private String description;

    @Column(name = "color_identifier")
    private String colorIdentifier;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "taskGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Task> tasks;

}
