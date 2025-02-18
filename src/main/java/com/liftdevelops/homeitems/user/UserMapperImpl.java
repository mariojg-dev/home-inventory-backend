package com.liftdevelops.homeitems.user;

import com.liftdevelops.homeitems.user.dto.UserDto;
import com.liftdevelops.homeitems.user.dto.UserResponseDto;
import com.liftdevelops.homeitems.user.model.UserEntity;
import com.liftdevelops.homeitems.util.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements Mapper<UserEntity, UserDto> {

    private final ModelMapper modelMapper;

    public UserMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto mapTo(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserEntity mapFrom(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }

    public UserResponseDto mapToUserResponseDto(UserEntity userEntity) {
        return new UserResponseDto(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail());
    }
}
