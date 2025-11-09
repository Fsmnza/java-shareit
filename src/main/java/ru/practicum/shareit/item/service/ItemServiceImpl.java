package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.ItemRepository;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.model.Status;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public List<Item> getAllItems() {
        return itemRepository.getAllItem();
    }

    @Override
    public Item getById(long id) {
        return itemRepository.getById(id);
    }

    @Override
    public Item createNewItem(ItemDto itemDto, long newId) {
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setStatus(Boolean.TRUE.equals(itemDto.getAvailable()) ? Status.AVAILABLE : Status.NOT_AVAILABLE);
        item.setOwnerId(newId);
        return itemRepository.createItem(item);
    }

    @Override
    public List<Item> search(String text) {
        return itemRepository.getAllItem().stream()
                .filter(item -> item.getStatus() == Status.AVAILABLE)
                .filter(item -> item.getName().toLowerCase().contains(text.toLowerCase()) ||
                                item.getDescription().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Item updateItem(ItemDto dto, long id, long userId) {
        Item existing = itemRepository.getById(id);
        if (!existing.getOwnerId().equals(userId)) return null;
        return itemRepository.updateItemById(existing, dto);
    }

    @Override
    public Item deleteItemById(long id) {
        return itemRepository.deleteItemById(id);
    }
}
