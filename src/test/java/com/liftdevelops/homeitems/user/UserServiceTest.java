package com.liftdevelops.homeitems.user;

import com.liftdevelops.homeitems.TestDataUtil;
import com.liftdevelops.homeitems.user.dto.UserResponseDto;
import com.liftdevelops.homeitems.user.model.UserEntity;
import com.liftdevelops.homeitems.user.repository.UserRepository;
import com.liftdevelops.homeitems.user.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testThatUserIsUpdatedAndReturned() {

        UserEntity userEntity = TestDataUtil.createTestUserEntityA();

        UserEntity modifiedEntity = TestDataUtil.createTestUserEntityA();
        modifiedEntity.setEmail("newEmail@gmail.com");
        modifiedEntity.setUsername("NewUserName");

        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(userRepository.save(TestDataUtil.createTestUserEntityA())).thenReturn(modifiedEntity);

        UserResponseDto result = userService.partialUpdate(1L, modifiedEntity);

        assertEquals("newEmail@gmail.com", result.email());
        assertEquals("NewUserName", result.username());
        verify(userRepository).save(any(UserEntity.class));

    }

    @Test
    void findById() {

        when(userRepository.findById(1L)).thenReturn(Optional.of(TestDataUtil.createTestUserEntityA()));

        Optional<UserEntity> persistedUser = userService.findById(1L);

        assertEquals(1, persistedUser.get().getId());

    }

    @Test
    void delete() {

        userService.delete(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

}
