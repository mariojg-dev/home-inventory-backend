package com.liftdevelops.homeitems.item;

import com.liftdevelops.homeitems.constants.ApiConstants;
import com.liftdevelops.homeitems.util.Mapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//TODO add Exception Handling
@RestController
@RequestMapping(ApiConstants.API_VERSION + "/items")
class ItemController {

    private final ItemService itemService;
    private final Mapper<ItemEntity, ItemDto> itemMapper;
    public ItemController(ItemService itemService, Mapper<ItemEntity, ItemDto> itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@Valid @RequestBody ItemDto itemDto) {
        ItemEntity itemEntity = itemMapper.mapFrom(itemDto);
        ItemEntity savedItemEntity = itemService.createItem(itemEntity);
        return new ResponseEntity<>(itemMapper.mapTo(savedItemEntity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ItemDto>> getAllItems(Pageable pageable) {
        Page<ItemEntity> itemEntities = itemService.getAllItems(pageable);
        Page<ItemDto> itemDtos = itemEntities.map(itemMapper::mapTo);
        return ResponseEntity.ok(itemDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable("id") Long id) {
        Optional<ItemEntity> foundEntity = itemService.findById(id);
        return foundEntity.map(itemEntity -> {
            ItemDto itemDto = itemMapper.mapTo(itemEntity);
            return new ResponseEntity<>(itemDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ItemDto>> searchItems(@RequestParam String name, @RequestParam String description, Pageable pageable) {
        Page<ItemEntity> itemEntities = itemService.searchByNameOrDescription(name, description, pageable);
        Page<ItemDto> itemDtos = itemEntities.map(itemMapper::mapTo);
        return ResponseEntity.ok(itemDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> updateItem(@PathVariable Long id, @RequestBody ItemDto itemDto) {
        ItemEntity itemEntity = itemMapper.mapFrom(itemDto);
        boolean itemExists = itemService.isPersisted(id);
        ItemEntity updatedItemEntity = itemService.updateItem(id, itemEntity);
        ItemDto updatedItemDto = itemMapper.mapTo(updatedItemEntity);

        if (itemExists) {
            return new ResponseEntity<>(updatedItemDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(updatedItemDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ItemDto> partiallyUpdateItem(@PathVariable Long id, @RequestBody ItemDto itemDto) {
        if (!itemService.isPersisted(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ItemEntity itemEntity = itemMapper.mapFrom(itemDto);
        ItemEntity updatedItemEntity = itemService.partialUpdate(id, itemEntity);
        ItemDto updatedItemDto = itemMapper.mapTo(updatedItemEntity);
        return new ResponseEntity<>(updatedItemDto, HttpStatus.OK);
    }
}
