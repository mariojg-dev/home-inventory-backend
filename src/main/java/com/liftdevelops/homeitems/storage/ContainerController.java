package com.liftdevelops.homeitems.storage;

import com.liftdevelops.homeitems.constants.ApiConstants;
import com.liftdevelops.homeitems.util.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiConstants.API_VERSION + "/containers")
class ContainerController {

    private final ContainerService containerService;
    private final Mapper<ContainerEntity, ContainerDto> containerMapper;

    public ContainerController(ContainerService containerService, Mapper<ContainerEntity, ContainerDto> containerMapper) {
        this.containerService = containerService;
        this.containerMapper = containerMapper;
    }

    @PostMapping
    public ResponseEntity<ContainerDto> createContainer(@RequestBody ContainerDto containerDto) {
        ContainerEntity containerEntity = containerMapper.mapFrom(containerDto);
        ContainerEntity savedEntity = containerService.createContainer(containerEntity);
        return new ResponseEntity<>(containerMapper.mapTo(savedEntity), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ContainerDto> getAllItems() {
        List<ContainerEntity> containerEntities = containerService.findAll();
        return containerEntities.stream()
                .map(containerMapper::mapTo)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContainerDto> updateContainer(@PathVariable Long id, @RequestBody ContainerDto containerDto) {
        ContainerEntity containerEntity = containerMapper.mapFrom(containerDto);
        boolean containerExists = containerService.isPersisted(id);
        ContainerEntity updatedContainerEntity = containerService.updateContainer(id, containerEntity);
        ContainerDto updatedContainerDto = containerMapper.mapTo(updatedContainerEntity);

        if (containerExists) {
            return new ResponseEntity<>(updatedContainerDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(updatedContainerDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContainer(@PathVariable Long id) {
        containerService.deleteContainer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
