package com.notic.user.services;

import com.notic.auth.authentication.dtos.request.UserRegisterDTO;
import com.notic.auth.authentication.dtos.response.UserRegisteredDTO;
import com.notic.user.domain.User;
import com.notic.user.mappers.UserMapper;
import com.notic.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User saveUser(UserRegisterDTO userRegisterDTO, String passwordEncrypted) {
        //Check that the email is not already registered
        if(findUserByEmail(userRegisterDTO.email()) != null){
            // Throw exception email registered
        }
        final User user =  userMapper.userRegisterToUser(userRegisterDTO);
        user.setPassword(passwordEncrypted);

        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }

}
