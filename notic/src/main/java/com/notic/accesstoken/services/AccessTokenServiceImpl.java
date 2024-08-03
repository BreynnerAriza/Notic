package com.notic.accesstoken.services;

import com.notic.accesstoken.domain.AccessToken;
import com.notic.accesstoken.repositories.AccessTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccessTokenServiceImpl implements IAccessTokenService {

    private final AccessTokenRepository accessTokenRepository;

    @Override
    @Transactional
    public void saveToken(AccessToken accessToken) {
        accessTokenRepository.save(accessToken);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<AccessToken> getAllTokenValidByUser(Integer userId) {
        return accessTokenRepository.findAllTokenValidByUser(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public AccessToken getAccessTokenByToken(String token) {
        return accessTokenRepository.findAccessTokenByToken(token).orElse(null);
    }

}
