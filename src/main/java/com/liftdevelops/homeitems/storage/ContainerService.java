package com.liftdevelops.homeitems.storage;

import java.util.List;
import java.util.Optional;

interface ContainerService {

    ContainerEntity createContainer(ContainerEntity containerEntity);
    Optional<ContainerEntity> findById(Long id);
    List<ContainerEntity> findAll();
    ContainerEntity updateContainer(Long id, ContainerEntity containerEntity);
    void deleteContainer(Long id);
    boolean isPersisted(Long id);

}
