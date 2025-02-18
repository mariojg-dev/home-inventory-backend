package com.liftdevelops.homeitems.storage;

import com.liftdevelops.homeitems.item.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerDto {
    private Long id;
    private String name;
    private String color;
    private String location;
    private List<ItemDto> items;
}
