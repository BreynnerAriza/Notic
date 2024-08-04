package com.notic.user.services;

import com.notic.auth.dtos.request.UserRegisterDTO;
import com.notic.common.exceptions.customexceptions.AlreadyExists;
import com.notic.user.domain.User;
import com.notic.user.mappers.UserMapper;
import com.notic.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.notic.auth.constants.AuthenticationExceptionConstants.EMAIL_ALREADY_EXISTS;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User saveUser(UserRegisterDTO userRegisterDTO, String passwordEncrypted) {
        if(findUserByEmail(userRegisterDTO.email()) != null){
            throw new AlreadyExists(
                    EMAIL_ALREADY_EXISTS.getTitle(),
                    EMAIL_ALREADY_EXISTS.getMessage(),
                    EMAIL_ALREADY_EXISTS.getStatus()
            );
        }
        final User user =  userMapper.userRegisterToUser(userRegisterDTO);
        user.setActive(Boolean.TRUE);
        user.setPassword(passwordEncrypted);

        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }

}
