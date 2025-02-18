package com.liftdevelops.homeitems.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    //Search the items by the given username or description
    Page<ItemEntity> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description, Pageable pageable);

}
