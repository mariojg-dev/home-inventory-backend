package com.liftdevelops.homeitems.user;

import com.liftdevelops.homeitems.TestDataUtil;
import com.liftdevelops.homeitems.user.dto.UserResponseDto;
import com.liftdevelops.homeitems.user.model.UserEntity;
import com.liftdevelops.homeitems.user.repository.UserRepository;
import com.liftdevelops.homeitems.user.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Captor
    private ArgumentCaptor<UserEntity> userEntityCaptor;

    @Test
    void testThatPartialUpdateUpdatesUserPropertiesAndReturnsResponseDto() {

        UserEntity existingUser = TestDataUtil.createTestUserEntityA();
        UserEntity updateData = TestDataUtil.createTestUserEntityA();
        updateData.setEmail("newEmail@gmail.com");
        updateData.setUsername("NewUserName");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserResponseDto result = userService.partialUpdate(1L, updateData);

        assertEquals("newEmail@gmail.com", result.email());
        assertEquals("NewUserName", result.username());

        // verify and capture the saved entity
        verify(userRepository).save(userEntityCaptor.capture());
        UserEntity savedEntity = userEntityCaptor.getValue();
        assertEquals("newEmail@gmail.com", savedEntity.getEmail());
        assertEquals("NewUserName", savedEntity.getUsername());


        assertEquals(existingUser.getId(), savedEntity.getId());
    }

    @Test
    void testThatPartialUpdateThrowsExceptionWhenUserNotFound() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        UserEntity updateData = TestDataUtil.createTestUserEntityA();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.partialUpdate(2L, updateData);
        });

        assertTrue(exception.getMessage().contains("could not be updated"));
        verify(userRepository, never()).save(any());
    }

    @Test
    void testThatFindByIdReturnsUserWhenUserExists() {

        UserEntity expectedUser = TestDataUtil.createTestUserEntityA();
        when(userRepository.findById(1L)).thenReturn(Optional.of(expectedUser));

        Optional<UserEntity> result = userService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(expectedUser.getId(), result.get().getId());
        assertEquals(expectedUser.getUsername(), result.get().getUsername());
        assertEquals(expectedUser.getEmail(), result.get().getEmail());
        verify(userRepository).findById(1L);
    }

    @Test
    void testThatCreateUserSavesUserAndReturnsResponseDto() {

        UserEntity userToCreate = TestDataUtil.createTestUserEntityA();
        when(userRepository.save(any(UserEntity.class))).thenReturn(userToCreate);

        UserResponseDto result = userService.createUser(userToCreate);

        assertNotNull(result);
        assertEquals(userToCreate.getId(), result.id());
        assertEquals(userToCreate.getUsername(), result.username());
        assertEquals(userToCreate.getEmail(), result.email());
        verify(userRepository).save(userToCreate);
    }

}