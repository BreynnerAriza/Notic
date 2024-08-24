package com.notic.accesstoken.business.services.impl;

import com.notic.accesstoken.business.services.IAccessTokenService;
import com.notic.accesstoken.persistence.entities.AccessToken;
import com.notic.accesstoken.persistence.repositories.AccessTokenRepository;
import com.notic.common.exceptions.customexceptions.TokenInvalidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.notic.common.exceptions.constants.ExceptionConstants.TOKEN_INVALID;

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
    public List<AccessToken> getAllTokenValidByUser(Integer userId) {
        return accessTokenRepository.findAllTokenValidByUser(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public AccessToken getAccessTokenByToken(String token) {
        return accessTokenRepository.findAccessTokenByToken(token).orElseThrow(
                () -> new TokenInvalidException(
                        TOKEN_INVALID.getTitle(), TOKEN_INVALID.getMessage(), TOKEN_INVALID.getStatus()
                )
        );
    }

    @Override
    public void invalidatedToken(AccessToken accessToken) {
        accessToken.setExpired(Boolean.TRUE);
        accessToken.setRevoked(Boolean.TRUE);
        accessTokenRepository.save(accessToken);
    }

    @Override
    public boolean isRefreshToken(String token) {
        return accessTokenRepository.findAccessTokenByToken(token).isPresent();
    }

}
