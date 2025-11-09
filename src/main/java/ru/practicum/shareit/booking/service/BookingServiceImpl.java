package ru.practicum.shareit.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.BookingRepository;
import ru.practicum.shareit.booking.model.Booking;


import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository repository;

    public Booking createBooking(Booking booking) {
        return repository.createBooking(booking);
    }

    public List<Booking> getAllBookings() {
        return repository.getAllBookings();
    }
}
