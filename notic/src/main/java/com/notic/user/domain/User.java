package com.notic.user.domain;

import com.notic.taskgroup.domain.TaskGroup;
import com.notic.accesstoken.domain.AccessToken;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode(exclude = {"accessTokens", "taskGroups"})
@ToString(exclude = {"accessTokens", "taskGroups"})
public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    private String email;
    private String password;
    private String names;
    private String surnames;
    private Boolean active = Boolean.TRUE;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<AccessToken> accessTokens;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch =FetchType.LAZY)
    private Set<TaskGroup> taskGroups;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }


}
