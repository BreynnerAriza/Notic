package com.notic.accesstoken.services;

import com.notic.accesstoken.domain.AccessToken;

import java.util.Set;

public interface IAccessTokenService {

    void saveToken(AccessToken accessToken);
    Set<AccessToken> getAllTokenValidByUser(Integer userId);
    AccessToken getAccessTokenByToken(String token);

}
