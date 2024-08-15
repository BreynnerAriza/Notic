package com.notic.user.mappers;

import com.notic.auth.dtos.request.UserRegisterDTO;
import com.notic.auth.dtos.response.UserRegisteredDTO;
import com.notic.user.domain.User;
import com.notic.user.dtos.response.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User userRegisterToUser(UserRegisterDTO userRegister);
    UserRegisteredDTO userToUserRegistered(User user);
    UserResponseDTO userToUserResponse(User user);

}
