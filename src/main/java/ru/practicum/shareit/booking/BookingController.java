package ru.practicum.shareit.booking;

import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.item.ItemRepository;

import java.util.List;

/**
 * TODO Sprint add-bookings.
 */
@RestController
@RequestMapping(path = "/bookings")
public class BookingController {

    private final BookingService service;
    private final ItemRepository itemRepository;

    public BookingController(BookingService service, ItemRepository itemRepository) {
        this.service = service;
        this.itemRepository = itemRepository;
    }

    @PostMapping
    public Booking createBooking(@RequestBody BookingDto dto,
                                 @RequestHeader("X-Sharer-User-Id") long userId) {

        Booking booking = new Booking();
        booking.setBookerId(userId);
        booking.setStart(dto.getStart());
        booking.setEnd(dto.getEnd());
        booking.setItem(itemRepository.getById(dto.getItemId()));

        return service.createBooking(booking);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return service.getAllBookings();
    }
}
