package com.liftdevelops.homeitems.item;

import org.springframework.stereotype.Service;

@Service
interface ItemService {

    ItemEntity createEntity(ItemEntity itemEntity);
}
