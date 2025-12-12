package ru.practicum.shareit.booking;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;

/**
 * Контроллер для работы с бронированиями через Gateway.
 */
@Slf4j
@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingClient bookingClient;

    /**
     * Создать новое бронирование.
     *
     * @param userId ID пользователя, создающего бронирование
     * @param dto    данные бронирования
     * @return информация о созданном бронировании
     */
    @PostMapping
    public ResponseEntity<Object> createBooking(
            @RequestHeader("X-Sharer-User-Id") Long userId,
            @Valid @RequestBody BookingDto dto) {
        log.info("Gateway: создать бронирование для пользователя {}", userId);
        return bookingClient.createBooking(userId, dto);
    }

    /**
     * Одобрить или отклонить бронирование владельцем вещи.
     *
     * @param ownerId   ID владельца вещи
     * @param bookingId ID бронирования
     * @param approved  true — одобрено, false — отклонено
     * @return информация о бронировании с обновленным статусом
     */
    @PatchMapping("/{bookingId}")
    public ResponseEntity<Object> approveBooking(
            @RequestHeader("X-Sharer-User-Id") Long ownerId,
            @PathVariable Long bookingId,
            @RequestParam boolean approved) {
        log.info(
                "Gateway: одобрение бронирования {} владельцем {} (approved={})",
                bookingId, ownerId, approved
        );
        return bookingClient.approveBooking(ownerId, bookingId, approved);
    }

    /**
     * Получить информацию о конкретном бронировании.
     *
     * @param userId    ID пользователя
     * @param bookingId ID бронирования
     * @return информация о бронировании
     */
    @GetMapping("/{bookingId}")
    public ResponseEntity<Object> getBooking(
            @RequestHeader("X-Sharer-User-Id") Long userId,
            @PathVariable Long bookingId) {
        log.info("Gateway: получить бронирование {} пользователя {}", bookingId, userId);
        return bookingClient.getBooking(userId, bookingId);
    }

    /**
     * Получить список бронирований пользователя по статусу.
     *
     * @param userId ID пользователя
     * @param state  статус бронирований (ALL, CURRENT, PAST, FUTURE и др.)
     * @return список бронирований пользователя
     */
    @GetMapping
    public ResponseEntity<Object> getBookingsByBooker(
            @RequestHeader("X-Sharer-User-Id") Long userId,
            @RequestParam(defaultValue = "ALL") String state) {
        log.info(
                "Gateway: получить бронирования пользователя {} со статусом {}",
                userId, state
        );
        return bookingClient.getBookingsByBooker(userId, state);
    }

    /**
     * Получить список бронирований вещей владельца по статусу.
     *
     * @param userId ID владельца
     * @param state  статус бронирований (ALL, CURRENT, PAST, FUTURE и др.)
     * @return список бронирований для владельца
     */
    @GetMapping("/owner")
    public ResponseEntity<Object> getBookingsForOwner(
            @RequestHeader("X-Sharer-User-Id") Long userId,
            @RequestParam(defaultValue = "ALL") String state) {
        log.info(
                "Gateway: получить бронирования для владельца {} со статусом {}",
                userId, state
        );
        return bookingClient.getBookingsForOwner(userId, state);
    }
}
