package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Validated
public class ItemController {
    private final ItemServiceImpl itemService;

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@RequestHeader("X-Sharer-User-Id") Long userId,
                                              @RequestBody @Validated(ItemDto.OnCreate.class) ItemDto dto) {
        ItemDto created = itemService.createItem(userId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<ItemDto> updateItem(
            @RequestHeader("X-Sharer-User-Id") Long userId,
            @PathVariable Long itemId,
            @RequestBody ItemDto dto) {
        ItemDto updated = itemService.updateItem(userId, itemId, dto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDto> getItem(@RequestHeader("X-Sharer-User-Id") Long userId,
                                           @PathVariable Long itemId) {
        ItemDto dto = itemService.getItemById(userId, itemId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> getOwnerItems(@RequestHeader("X-Sharer-User-Id") Long userId) {
        List<ItemDto> list = itemService.getItemsByOwner(userId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ItemDto>> searchItems(@RequestParam(name = "text", required = false) String text) {
        List<ItemDto> result = itemService.searchItems(text);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{itemId}/comment")
    public ResponseEntity<CommentDto> addComment(@RequestHeader("X-Sharer-User-Id") Long userId,
                                                 @PathVariable Long itemId,
                                                 @RequestBody @Valid CommentDto commentDto) {
        CommentDto created = itemService.addComment(userId, itemId, commentDto);
        return ResponseEntity.ok(created);
    }
}