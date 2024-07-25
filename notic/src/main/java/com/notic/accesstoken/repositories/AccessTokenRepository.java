package com.notic.accesstoken.repositories;

import com.notic.accesstoken.domain.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Integer> {

    @Query("""
        SELECT t FROM AccessToken t
        WHERE
        t.user.userId = :userId AND NOT t.expired AND NOT t.revoked
    """)
    Set<AccessToken> findAllTokenValidByUser(@Param("userId") Integer userId);

    Optional<AccessToken> findAccessTokenByToken(String token);

}
