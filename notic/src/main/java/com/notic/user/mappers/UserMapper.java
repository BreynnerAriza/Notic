package com.notic.user.mappers;

import ch.qos.logback.core.model.ModelConstants;
import com.notic.auth.authentication.dtos.request.UserRegisterDTO;
import com.notic.auth.authentication.dtos.response.UserRegisteredDTO;
import com.notic.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User userRegisterToUser(UserRegisterDTO userRegister);
    UserRegisteredDTO userToUserRegistered(User user);

}
