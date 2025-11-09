package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.service.BookingService;

import java.util.List;

@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService service;

    @PostMapping
    public BookingDto createBooking(@RequestBody BookingDto dto,
                                    @RequestHeader("X-Sharer-User-Id") long userId) {
        return service.createBooking(dto, userId);
    }

    @GetMapping
    public List<BookingDto> getAllBookings() {
        return service.getAllBookings();
    }
}
