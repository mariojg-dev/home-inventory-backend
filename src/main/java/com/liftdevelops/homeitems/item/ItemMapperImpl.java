package com.liftdevelops.homeitems.item;

import com.liftdevelops.homeitems.util.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
class ItemMapperImpl implements Mapper<ItemEntity, ItemDto> {

    private final ModelMapper modelMapper;

    public ItemMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ItemDto mapTo(ItemEntity itemEntity) {
        return modelMapper.map(itemEntity, ItemDto.class);
    }

    @Override
    public ItemEntity mapFrom(ItemDto itemDto) {
        return modelMapper.map(itemDto, ItemEntity.class);
    }
}
