package ru.practicum.shareit.booking.service;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingResponseDto;
import ru.practicum.shareit.booking.model.BookingState;

import java.util.List;

public interface BookingService {
    BookingResponseDto createBooking(Long userId, BookingDto dto);

    BookingResponseDto approveBooking(Long ownerId, Long bookingId, boolean approved);

    BookingResponseDto getBookingById(Long userId, Long bookingId);

    List<BookingResponseDto> getBookingsByBooker(Long userId, BookingState state);

    List<BookingResponseDto> getBookingsForOwner(Long userId, BookingState state);
}
