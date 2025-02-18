package com.liftdevelops.homeitems.user.service;

import com.liftdevelops.homeitems.user.dto.UserResponseDto;
import com.liftdevelops.homeitems.user.model.UserEntity;

import java.util.Optional;

public interface UserService {

    UserResponseDto createUser(UserEntity userEntity);

    Optional<UserEntity> findById(Long id);

    UserResponseDto partialUpdate(Long id, UserEntity userEntity);

    void delete(Long id);

    boolean isPersisted(Long id);
}
