package ru.practicum.shareit.booking.mapper;

import ru.practicum.shareit.booking.dto.BookingResponseDto;
import ru.practicum.shareit.booking.Booking;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.UserDto;

/**
 * Утилитный класс для преобразования {@link Booking} в {@link BookingResponseDto}.
 */
public class BookingResponseMapper {

    /**
     * Преобразует объект {@link Booking} в DTO для ответа {@link BookingResponseDto}.
     *
     * @param booking сущность бронирования
     * @return DTO с полной информацией о бронировании, или null если booking равен null
     */
    public static BookingResponseDto toBookingResponseDto(Booking booking) {
        if (booking == null) {
            return null;
        }
        return BookingResponseDto.builder()
                .id(booking.getId())
                .start(booking.getStart())
                .end(booking.getEnd())
                .status(booking.getStatus())
                .item(ItemDto.builder()
                        .id(booking.getItem().getId())
                        .name(booking.getItem().getName())
                        .description(booking.getItem().getDescription())
                        .available(booking.getItem().getAvailable())
                        .build())
                .booker(UserDto.builder()
                        .id(booking.getBookingUser().getId())
                        .name(booking.getBookingUser().getName())
                        .email(booking.getBookingUser().getEmail())
                        .build())
                .build();
    }
}
