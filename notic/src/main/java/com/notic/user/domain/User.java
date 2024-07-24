package com.notic.user.domain;

import com.notic.taskgroup.domain.TaskGroup;
import com.notic.token.domain.AccessToken;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity(name = "USERS")
@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode(exclude = {"accessTokens", "taskGroups"})
@ToString(exclude = {"accessTokens", "taskGroups"})
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    private String email;
    private String password;
    private String names;
    private String surnames;
    private Boolean active;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<AccessToken> accessTokens;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch =FetchType.LAZY)
    private Set<TaskGroup> taskGroups;

}
