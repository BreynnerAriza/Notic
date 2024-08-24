package com.notic.user.business.services;

import com.notic.user.persistence.entities.User;

public interface IUserService {

    User saveUser(User user);
    User findUserByEmail(String email);


}
