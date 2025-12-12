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
public final class ItemRequestControllerServer {

    private final ItemRequestService itemRequestService;

    /**
     * Создать новый запрос на вещь.
     *
     * @param userId идентификатор пользователя
     * @param dto данные запроса
     * @return созданный запрос
     */
    @PostMapping
    public ResponseEntity<ItemRequestDto> createRequest(@RequestHeader("X-Sharer-User-Id") final Long userId,
                                                        @RequestBody final ItemRequestDto dto) {
        return ResponseEntity.ok(itemRequestService.createRequest(userId, dto));
    }

    /**
     * Получить список запросов пользователя.
     *
     * @param userId идентификатор пользователя
     * @return список запросов
     */
    @GetMapping
    public ResponseEntity<List<ItemRequestDto>> getRequestsByUser(
            @RequestHeader("X-Sharer-User-Id") final Long userId) {
        return ResponseEntity.ok(itemRequestService.getRequestsByUser(userId));
    }

    /**
     * Получить список всех запросов, кроме запросов пользователя.
     *
     * @param userId идентификатор пользователя
     * @return список запросов
     */
    @GetMapping("/all")
    public ResponseEntity<List<ItemRequestDto>> getAllRequests(
            @RequestHeader("X-Sharer-User-Id") final Long userId) {
        return ResponseEntity.ok(itemRequestService.getAllRequests(userId));
    }

    /**
     * Получить запрос по идентификатору.
     *
     * @param userId идентификатор пользователя
     * @param requestId идентификатор запроса
     * @return запрос
     */
    @GetMapping("/{requestId}")
    public ResponseEntity<ItemRequestDto> getRequestById(
            @RequestHeader("X-Sharer-User-Id") final Long userId,
            @PathVariable final Long requestId) {
        ItemRequestDto dto = itemRequestService.getRequestById(userId, requestId);
        return ResponseEntity.ok(dto);
    }
}
