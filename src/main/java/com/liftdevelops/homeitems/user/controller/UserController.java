package com.liftdevelops.homeitems.user.controller;

import com.liftdevelops.homeitems.constants.ApiConstants;
import com.liftdevelops.homeitems.user.dto.UserDto;
import com.liftdevelops.homeitems.user.dto.UserResponseDto;
import com.liftdevelops.homeitems.user.model.UserEntity;
import com.liftdevelops.homeitems.user.service.UserServiceImpl;
import com.liftdevelops.homeitems.util.Mapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.API_VERSION + "/users")
public class UserController {

    private final UserServiceImpl userService;
    private final Mapper<UserEntity, UserDto> userMapper;

    //TODO add validation for existing username and email
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserDto userDto) {
        UserEntity userEntity = userMapper.mapFrom(userDto);
        UserResponseDto userResponseDto = userService.createUser(userEntity);

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    //TODO implement getUser for login


    @Operation(summary = "Update User Email or Username", description = "Update User Email or Username")
    @PatchMapping("{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        boolean userExists = userService.isPersisted(id);

        if (!userExists) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserEntity userEntity = userMapper.mapFrom(userDto);
        UserResponseDto updatedUser = userService.partialUpdate(id, userEntity);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

}
