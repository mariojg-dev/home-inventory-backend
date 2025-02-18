package com.liftdevelops.homeitems.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ContainerRepository extends JpaRepository<ContainerEntity, Long> {

}