package com.liftdevelops.homeitems;

import com.liftdevelops.homeitems.user.model.UserEntity;

public class TestDataUtil {

    public static UserEntity createTestUserA() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("email@gmail.com");
        userEntity.setRole("standard");
        userEntity.setUsername("TestUser");
        return userEntity;
    }

}
