package ru.practicum.shareit.booking.service;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingResponseDto;
import ru.practicum.shareit.booking.BookingState;

import java.util.List;

public interface BookingService {

    BookingResponseDto createBooking(final Long userId, final BookingDto dto);

    BookingResponseDto approveBooking(final Long ownerId, final Long bookingId, final boolean approved);

    BookingResponseDto getBookingById(final Long userId, final Long bookingId);

    List<BookingResponseDto> getBookingsByBooker(final Long userId, final BookingState state);

    List<BookingResponseDto> getBookingsForOwner(final Long userId, final BookingState state);
}
