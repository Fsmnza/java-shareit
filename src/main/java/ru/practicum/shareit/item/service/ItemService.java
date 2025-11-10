package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemService {
    List<Item> getAllItems();

    Item getById(long id);

    Item createNewItem(ItemDto item, long newId);

    List<Item> search(String text);

    Item updateItem(ItemDto dto, long id, long userId);

    Item deleteItemById(long id);
}
