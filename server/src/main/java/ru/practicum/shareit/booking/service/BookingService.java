package ru.practicum.shareit.booking.service;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingResponseDto;
import ru.practicum.shareit.booking.BookingState;

import java.util.List;

public interface BookingService {

    /**
     * Создать новое бронирование.
     *
     * @param userId идентификатор пользователя
     * @param dto данные бронирования
     * @return информация о созданном бронировании
     */
    BookingResponseDto createBooking(final Long userId, final BookingDto dto);

    /**
     * Подтвердить или отклонить бронирование владельцем.
     *
     * @param ownerId идентификатор владельца вещи
     * @param bookingId идентификатор бронирования
     * @param approved true для подтверждения, false для отклонения
     * @return обновленное бронирование
     */
    BookingResponseDto approveBooking(final Long ownerId, final Long bookingId, final boolean approved);

    /**
     * Получить бронирование по идентификатору.
     *
     * @param userId идентификатор пользователя (владелец или арендатор)
     * @param bookingId идентификатор бронирования
     * @return информация о бронировании
     */
    BookingResponseDto getBookingById(final Long userId, final Long bookingId);

    /**
     * Получить все бронирования пользователя по статусу.
     *
     * @param userId идентификатор пользователя
     * @param state статус бронирования
     * @return список бронирований
     */
    List<BookingResponseDto> getBookingsByBooker(final Long userId, final BookingState state);

    /**
     * Получить все бронирования вещей пользователя по статусу.
     *
     * @param userId идентификатор владельца
     * @param state статус бронирования
     * @return список бронирований
     */
    List<BookingResponseDto> getBookingsForOwner(final Long userId, final BookingState state);
}
