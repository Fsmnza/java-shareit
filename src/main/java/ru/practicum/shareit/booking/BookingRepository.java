package ru.practicum.shareit.booking;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.booking.model.Booking;

import java.util.*;

@Repository
public class BookingRepository {
    private final List<Booking> bookings = new ArrayList<>();
    private Long nextId = 1L;

    public Booking createBooking(Booking booking) {
        booking.setId(nextId++);
        bookings.add(booking);
        return booking;
    }

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }
}
