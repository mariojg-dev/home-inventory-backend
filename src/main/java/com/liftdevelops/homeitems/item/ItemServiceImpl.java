package com.liftdevelops.homeitems.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemEntity createItem(ItemEntity itemEntity) {
        return itemRepository.save(itemEntity);
    }

    @Override
    public Optional<ItemEntity> findById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public Page<ItemEntity> searchByNameOrDescription(String name, String description, Pageable pageable) {
        return itemRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(name, description, pageable);
    }

    @Override
    public Page<ItemEntity> getAllItems(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    @Override
    public ItemEntity updateItem(Long id, ItemEntity itemEntity) {
        Optional<ItemEntity> itemOptional = findById(id);
        ItemEntity updatedItem = itemOptional.orElseThrow(() -> new RuntimeException("Error occurred when updating Item. Item with ID: " + id + " not found."));

        return itemRepository.save(updatedItem);
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public boolean isPersisted(Long id) {
        return itemRepository.existsById(id);
    }

    @Override
    public ItemEntity partialUpdate(Long id, ItemEntity itemEntity) {
        itemEntity.setId(id);

        return itemRepository.findById(id).map(persistedItem -> {
            Optional.ofNullable(itemEntity.getName()).ifPresent(persistedItem::setName);
            Optional.ofNullable(itemEntity.getDescription()).ifPresent(persistedItem::setDescription);
            Optional.ofNullable(itemEntity.getPrice()).ifPresent(persistedItem::setPrice);
            Optional.ofNullable(itemEntity.getColor()).ifPresent(persistedItem::setColor);
            Optional.ofNullable(itemEntity.getCategory()).ifPresent(persistedItem::setCategory);

            return itemRepository.save(itemEntity);
        }).orElseThrow(() -> new RuntimeException("Item to update not found"));
    }


}
