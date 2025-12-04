package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.service.ItemRequestService;

import java.util.List;

@RestController("itemRequestControllerServer")
@RequestMapping("/requests")
@RequiredArgsConstructor
@Slf4j
public class ItemRequestControllerServer {
    private final ItemRequestService itemRequestService;

    @PostMapping
    public ResponseEntity<ItemRequestDto> createRequest(@RequestHeader("X-Sharer-User-Id") Long userId,
                                                        @RequestBody ItemRequestDto dto) {
        return ResponseEntity.ok(itemRequestService.createRequest(userId, dto));
    }

    @GetMapping
    public ResponseEntity<List<ItemRequestDto>> getRequestsByUser(@RequestHeader("X-Sharer-User-Id") Long userId) {
        return ResponseEntity.ok(itemRequestService.getRequestsByUser(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ItemRequestDto>> getAllRequests(@RequestHeader("X-Sharer-User-Id") Long userId) {
        return ResponseEntity.ok(itemRequestService.getAllRequests(userId));
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<ItemRequestDto> getRequestById(@RequestHeader("X-Sharer-User-Id") Long userId,
                                                         @PathVariable Long requestId) {
        ItemRequestDto dto = itemRequestService.getRequestById(userId, requestId);
        return ResponseEntity.ok(dto);
    }
}
