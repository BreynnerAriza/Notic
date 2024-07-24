package com.notic.token.domain;

import com.notic.token.constants.TokenType;
import com.notic.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ACCESS_TOKENS")
@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode
@ToString
public class AccessToken {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "access_token_id")
    private Integer accessTokenId;
    private String token;
    private Boolean expired;
    private Boolean revoked;

    @Enumerated(EnumType.STRING)
    @Column(name = "token_type")
    private TokenType tokenType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

}
