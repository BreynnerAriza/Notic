package com.notic.user.mappers;

import com.notic.auth.authentication.dtos.request.UserRegisterDTO;
import com.notic.auth.authentication.dtos.response.UserRegisteredDTO;
import com.notic.user.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel =  "spring")
public interface UserMapper {

    User userRegisterToUser(UserRegisterDTO userRegister);
    UserRegisteredDTO userToUserRegistered(User user);
}
