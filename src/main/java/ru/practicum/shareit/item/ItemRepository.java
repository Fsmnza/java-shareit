package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.model.Status;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ItemRepository {
    private Long nextId = 1L;
    private final List<Item> items = new ArrayList<>();

    public List<Item> getAllItem() {
        return new ArrayList<>(items);
    }

    public Item createItem(Item item) {
        item.setId(nextId++);
        items.add(item);
        return item;
    }

    public Item getById(long id) {
        return items.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found"));
    }


    public Item deleteItemById(long id) {
        Item item = getById(id);
        items.remove(item);
        return item;
    }

    public Item updateItemById(Item updateItem, ItemDto item) {
        if (item.getName() != null) updateItem.setName(item.getName());
        if (item.getDescription() != null) updateItem.setDescription(item.getDescription());
        if (item.getAvailable() != null)
            updateItem.setStatus(item.getAvailable() ? Status.AVAILABLE : Status.NOT_AVAILABLE);
        return updateItem;
    }

}
