package com.liftdevelops.homeitems.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//TODO temporär public, should be package private, just for test purposes
//rethink
@Repository
public interface ContainerRepository extends JpaRepository<ContainerEntity, Long> {

}