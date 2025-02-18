package com.liftdevelops.homeitems.user.service;

import com.liftdevelops.homeitems.user.dto.UserResponseDto;
import com.liftdevelops.homeitems.user.model.UserEntity;
import com.liftdevelops.homeitems.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    //private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
      //  this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDto createUser(UserEntity userEntity) {
        //String encodedPassword = passwordEncoder.encode(userEntity.getPassword());
        //userEntity.setPassword(encodedPassword);
        UserEntity savedUserEntity = userRepository.save(userEntity);

        return mapToUserResponseDto(savedUserEntity);
    }

    private UserResponseDto mapToUserResponseDto(UserEntity savedUserEntity) {
        return new UserResponseDto(savedUserEntity.getId(), savedUserEntity.getUsername(), savedUserEntity.getEmail());
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserResponseDto partialUpdate(Long id, UserEntity userEntity) {
        Optional<UserEntity> savedUserOpt = findById(id);
        UserEntity updatedUserEntity = savedUserOpt.orElseThrow(() -> new RuntimeException("User " + userEntity.getUsername() + "could not be updated. Not found"));

        BeanUtils.copyProperties(userEntity, updatedUserEntity, "password", "role", "id", "createdAt");

        UserEntity savedUserEntity = userRepository.save(updatedUserEntity);

        return mapToUserResponseDto(savedUserEntity);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean isPersisted(Long id) {
        return userRepository.existsById(id);
    }
}
