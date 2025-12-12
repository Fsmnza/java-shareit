package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingResponseDto;
import ru.practicum.shareit.booking.service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public final class BookingController {

    private final BookingService service;

    /**
     * Создать новое бронирование.
     */
    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(
            @RequestHeader("X-Sharer-User-Id") final Long userId,
            @RequestBody final BookingDto dto) {
        return ResponseEntity.ok(service.createBooking(userId, dto));
    }

    /**
     * Подтвердить или отклонить бронирование владельцем.
     */
    @PatchMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDto> approveBooking(
            @RequestHeader("X-Sharer-User-Id") final Long ownerId,
            @PathVariable final Long bookingId,
            @RequestParam final boolean approved) {
        return ResponseEntity.ok(service.approveBooking(ownerId, bookingId, approved));
    }

    /**
     * Получить бронирование по идентификатору.
     */
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDto> getBooking(
            @RequestHeader("X-Sharer-User-Id") final Long userId,
            @PathVariable final Long bookingId) {
        return ResponseEntity.ok(service.getBookingById(userId, bookingId));
    }

    /**
     * Получить все бронирования пользователя по статусу.
     */
    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getBookingsByBooker(
            @RequestHeader("X-Sharer-User-Id") final Long userId,
            @RequestParam(defaultValue = "ALL") final String state) {
        BookingState bookingState = BookingState.from(state);
        return ResponseEntity.ok(service.getBookingsByBooker(userId, bookingState));
    }

    /**
     * Получить все бронирования вещей пользователя по статусу.
     */
    @GetMapping("/owner")
    public ResponseEntity<List<BookingResponseDto>> getBookingsForOwner(
            @RequestHeader("X-Sharer-User-Id") final Long userId,
            @RequestParam(defaultValue = "ALL") final String state) {
        BookingState bookingState = BookingState.from(state);
        return ResponseEntity.ok(service.getBookingsForOwner(userId, bookingState));
    }
}
