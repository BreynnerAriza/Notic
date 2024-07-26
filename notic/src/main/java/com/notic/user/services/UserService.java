package com.notic.user.services;

import com.notic.auth.authentication.dtos.request.UserRegisterDTO;
import com.notic.auth.authentication.dtos.response.UserRegisteredDTO;
import com.notic.user.domain.User;

public interface UserService {

    User saveUser(UserRegisterDTO userRegisterDTO, String passwordEncrypted);
    User findUserByEmail(String email);

}
