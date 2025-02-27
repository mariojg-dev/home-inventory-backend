package com.liftdevelops.homeitems;

import com.liftdevelops.homeitems.item.ItemDto;
import com.liftdevelops.homeitems.item.ItemEntity;
import com.liftdevelops.homeitems.storage.ContainerEntity;
import com.liftdevelops.homeitems.user.dto.UserDto;
import com.liftdevelops.homeitems.user.model.Role;
import com.liftdevelops.homeitems.user.model.UserEntity;

import java.math.BigDecimal;

public class TestDataUtil {

    public static UserEntity createTestUserEntityA() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("email@gmail.com");
        userEntity.setRole(Role.ADMIN);
        userEntity.setUsername("TestUser");
        userEntity.setPassword("strongPassword123");
        return userEntity;
    }

    public static UserDto createTestUserDtoA() {
        return UserDto.builder()
                .email("email@gmail.com")
                .id(1L)
                .username("TestUser")
                .password("strongPassword123")
                .build();
    }

    public static ItemEntity createTestItemA() {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setCategory("Cable");
        itemEntity.setColor("Blue");
        itemEntity.setOwner(createTestUserEntityA());
        itemEntity.setPrice(BigDecimal.valueOf(19.99));
        itemEntity.setName("USB C to C");
        itemEntity.setDescription("25th Cable in der Schublade");
        return itemEntity;
    }

    public static ItemDto createTestItemDtoA() {
        return ItemDto.builder()
                .category("Cable")
                .color("Blue")
                .price(BigDecimal.valueOf(19.99))
                .name("USB C to C")
                .description("25th Cable in der Schublade")
                .build();
    }

    public static ContainerEntity createTestContainerA() {
        ContainerEntity containerEntity = new ContainerEntity();
        containerEntity.setName("Schublade");
        containerEntity.setColor("Black");
        containerEntity.setLocation("Wohnzimmer");
        return containerEntity;
    }
}
