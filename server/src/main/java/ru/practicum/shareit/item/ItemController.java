package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
public final class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@RequestHeader("X-Sharer-User-Id") final Long userId,
                                              @RequestBody final ItemDto dto) {
        return ResponseEntity.ok(itemService.createItem(userId, dto));
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<ItemDto> updateItem(@RequestHeader("X-Sharer-User-Id") final Long userId,
                                              @PathVariable final Long itemId,
                                              @RequestBody final ItemDto dto) {
        return ResponseEntity.ok(itemService.updateItem(userId, itemId, dto));
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDto> getItem(@RequestHeader("X-Sharer-User-Id") final Long userId,
                                           @PathVariable final Long itemId) {
        return ResponseEntity.ok(itemService.getItemById(userId, itemId));
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> getOwnerItems(
            @RequestHeader("X-Sharer-User-Id") final Long userId) {
        return ResponseEntity.ok(itemService.getItemsByOwner(userId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ItemDto>> searchItems(@RequestParam(name = "text") final String text) {
        if (text == null || text.isBlank()) {
            return ResponseEntity.ok(List.of());
        }
        return ResponseEntity.ok(itemService.searchItems(text));
    }

    @PostMapping("/{itemId}/comment")
    public ResponseEntity<CommentDto> addComment(@RequestHeader("X-Sharer-User-Id") final Long userId,
                                                 @PathVariable final Long itemId,
                                                 @RequestBody final CommentDto commentDto) {
        return ResponseEntity.ok(itemService.addComment(userId, itemId, commentDto));
    }
}
