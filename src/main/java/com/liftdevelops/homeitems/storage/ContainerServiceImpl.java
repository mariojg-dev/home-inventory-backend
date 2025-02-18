package com.liftdevelops.homeitems.storage;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
class ContainerServiceImpl implements ContainerService{

    private final ContainerRepository containerRepository;

    public ContainerServiceImpl(ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }

    @Override
    public ContainerEntity createContainer(ContainerEntity containerEntity) {
        return containerRepository.save(containerEntity);
    }

    @Override
    public List<ContainerEntity> findAll() {
        return new ArrayList<>(containerRepository.findAll());
    }

    @Override
    public Optional<ContainerEntity> findById(Long id) {
        return containerRepository.findById(id);
    }

    @Override
    public ContainerEntity updateContainer(Long id, ContainerEntity containerEntity) {
        Optional<ContainerEntity> containerOptional = findById(id);
        ContainerEntity updatedContainerEntity = containerOptional.orElseThrow(() -> new RuntimeException("Error occurred when updating Container. Container with ID: " + id + " not found."));
        return containerRepository.save(updatedContainerEntity);
    }

    @Override
    public void deleteContainer(Long id) {
        containerRepository.deleteById(id);
    }

    @Override
    public boolean isPersisted(Long id) {
        return containerRepository.existsById(id);
    }
}
