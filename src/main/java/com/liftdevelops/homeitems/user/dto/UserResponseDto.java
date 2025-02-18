package com.liftdevelops.homeitems.user.dto;

import com.liftdevelops.homeitems.serialization.MaskEmail;

public record UserResponseDto(Long id, String username, @MaskEmail String email) {
}
