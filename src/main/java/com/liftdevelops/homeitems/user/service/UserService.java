package com.liftdevelops.homeitems.user.service;

import com.liftdevelops.homeitems.user.model.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserEntity createUser(UserEntity userEntity);
    UserEntity findById();
    UserEntity findByUsername();
    UserEntity update();
}
