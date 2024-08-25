package com.notic.user.facade.mappers;

import com.notic.auth.facade.dtos.request.UserRegisterDTO;
import com.notic.auth.facade.dtos.response.UserRegisteredDTO;
import com.notic.user.facade.dtos.response.UserResponseDTO;
import com.notic.user.persistence.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userRegisterToUser(UserRegisterDTO userRegister);
    UserRegisteredDTO userToUserRegistered(User user);
    UserResponseDTO userToUserResponse(User user);

}
