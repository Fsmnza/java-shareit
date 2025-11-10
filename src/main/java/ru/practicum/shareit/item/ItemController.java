package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemServiceImpl;
import ru.practicum.shareit.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.shareit.item.dto.mapper.ItemMapper.toDto;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemServiceImpl itemService;
    private final UserRepository userRepository;

    @GetMapping
    public List<ItemDto> getAllItems(@RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemService.getAllItems().stream()
                .filter(i -> i.getOwnerId().equals(userId))
                .map(ItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ItemDto getById(@PathVariable long id) {
        return ItemMapper.toDto(itemService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@RequestBody @Valid ItemDto itemDto,
                                              @RequestHeader("X-Sharer-User-Id") Long userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (itemDto.getAvailable() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Item item = itemService.createNewItem(itemDto, userId);
        return ResponseEntity.ok(toDto(item));
    }


    @PatchMapping("{id}")
    public ResponseEntity<ItemDto> updateById(@RequestBody ItemDto itemDto,
                                              @PathVariable long id,
                                              @RequestHeader("X-Sharer-User-Id") Long userId) {
        Item updated = itemService.updateItem(itemDto, id, userId);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(ItemMapper.toDto(updated));
    }

    @DeleteMapping("{id}")
    public Item deleteItem(@PathVariable long id) {
        return itemService.deleteItemById(id);
    }

    @GetMapping("/search")
    public List<ItemDto> searchItems(@RequestParam String text) {
        if (text.isBlank()) return new ArrayList<>();
        return itemService.search(text).stream()
                .map(ItemMapper::toDto)
                .collect(Collectors.toList());
    }
}
