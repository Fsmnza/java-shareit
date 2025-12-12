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

    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(
            @RequestHeader("X-Sharer-User-Id") final Long userId,
            @RequestBody final BookingDto dto) {
        return ResponseEntity.ok(service.createBooking(userId, dto));
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDto> approveBooking(
            @RequestHeader("X-Sharer-User-Id") final Long ownerId,
            @PathVariable final Long bookingId,
            @RequestParam final boolean approved) {
        return ResponseEntity.ok(service.approveBooking(ownerId, bookingId, approved));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDto> getBooking(
            @RequestHeader("X-Sharer-User-Id") final Long userId,
            @PathVariable final Long bookingId) {
        return ResponseEntity.ok(service.getBookingById(userId, bookingId));
    }

    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getBookingsByBooker(
            @RequestHeader("X-Sharer-User-Id") final Long userId,
            @RequestParam(defaultValue = "ALL") final String state) {
        BookingState bookingState = BookingState.from(state);
        return ResponseEntity.ok(service.getBookingsByBooker(userId, bookingState));
    }

    @GetMapping("/owner")
    public ResponseEntity<List<BookingResponseDto>> getBookingsForOwner(
            @RequestHeader("X-Sharer-User-Id") final Long userId,
            @RequestParam(defaultValue = "ALL") final String state) {
        BookingState bookingState = BookingState.from(state);
        return ResponseEntity.ok(service.getBookingsForOwner(userId, bookingState));
    }
}
