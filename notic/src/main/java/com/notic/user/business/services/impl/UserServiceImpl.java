package com.notic.user.business.services.impl;

import com.notic.common.exceptions.customexceptions.AlreadyExistsException;
import com.notic.user.business.services.IUserService;
import com.notic.user.persistence.entities.User;
import com.notic.user.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.notic.auth.utils.constants.AuthenticationExceptionConstants.EMAIL_ALREADY_EXISTS;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User saveUser(User user) {
        if(userRepository.findUserByEmail(user.getEmail()).isPresent()){
            throw new AlreadyExistsException(
                    EMAIL_ALREADY_EXISTS.getTitle(),
                    EMAIL_ALREADY_EXISTS.getMessage(),
                    EMAIL_ALREADY_EXISTS.getStatus()
            );
        }
        user.setActive(Boolean.TRUE);
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }


}
