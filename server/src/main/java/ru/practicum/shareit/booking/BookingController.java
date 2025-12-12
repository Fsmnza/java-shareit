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
public class BookingController {
    private final BookingService service;

    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(@RequestHeader("X-Sharer-User-Id") Long userId,
                                                            @RequestBody BookingDto dto) {
        BookingResponseDto created = service.createBooking(userId, dto);
        return ResponseEntity.ok(created);
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDto> approveBooking(@RequestHeader("X-Sharer-User-Id") Long ownerId,
                                                             @PathVariable Long bookingId,
                                                             @RequestParam boolean approved) {
        BookingResponseDto updated = service.approveBooking(ownerId, bookingId, approved);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDto> getBooking(@RequestHeader("X-Sharer-User-Id") Long userId,
                                                         @PathVariable Long bookingId) {
        BookingResponseDto dto = service.getBookingById(userId, bookingId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getBookingsByBooker(
            @RequestHeader("X-Sharer-User-Id") Long userId,
            @RequestParam(defaultValue = "ALL") String state) {

        BookingState bookingState = BookingState.from(state);
        List<BookingResponseDto> list = service.getBookingsByBooker(userId, bookingState);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/owner")
    public ResponseEntity<List<BookingResponseDto>> getBookingsForOwner(
            @RequestHeader("X-Sharer-User-Id") Long userId,
            @RequestParam(defaultValue = "ALL") String state) {
        BookingState bookingState = BookingState.from(state);
        List<BookingResponseDto> list = service.getBookingsForOwner(userId, bookingState);
        return ResponseEntity.ok(list);
    }
}
