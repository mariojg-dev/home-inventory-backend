package com.liftdevelops.homeitems.item;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

interface ItemService {

    ItemEntity createItem(ItemEntity itemEntity);
    Optional<ItemEntity> findById(Long id);
    Page<ItemEntity> searchByNameOrDescription(String name, String description, Pageable pageable);
    Page<ItemEntity> getAllItems(Pageable pageable);
    ItemEntity updateItem(Long id, ItemEntity itemEntity);
    void deleteItem(Long id);
    boolean isPersisted(Long id);
    ItemEntity partialUpdate(Long id, ItemEntity itemEntity);
}
