package com.liftdevelops.homeitems.storage;

import com.liftdevelops.homeitems.util.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
class ContainerMapperImpl implements Mapper<ContainerEntity, ContainerDto> {

    private final ModelMapper modelMapper;

    public ContainerMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ContainerDto mapTo(ContainerEntity containerEntity) {
        return modelMapper.map(containerEntity, ContainerDto.class);
    }

    @Override
    public ContainerEntity mapFrom(ContainerDto containerDto) {
        return modelMapper.map(containerDto, ContainerEntity.class);
    }
}
