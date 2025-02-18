package com.liftdevelops.homeitems.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {
    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private String color;
    private BigDecimal price;
    private Long ownerId;
    private Long containerId;
    private String category;
    private LocalDateTime createdAt;
    private Set<Long> tagIds;
}
