package com.notic.accesstoken.business.services;

import com.notic.accesstoken.persistence.entities.AccessToken;

import java.util.List;
import java.util.Set;

public interface IAccessTokenService {

    void saveToken(AccessToken accessToken);
    List<AccessToken> getAllTokenValidByUser(Integer userId);
    AccessToken getAccessTokenByToken(String token);
    void invalidatedToken(AccessToken accessToken);
    boolean isRefreshToken(String token);

}
