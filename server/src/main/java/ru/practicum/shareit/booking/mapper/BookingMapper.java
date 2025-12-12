package ru.practicum.shareit.booking.mapper;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.Booking;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

/**
 * Утилитный класс для преобразования {@link BookingDto} в {@link Booking}.
 */
public class BookingMapper {

    /**
     * Преобразует DTO бронирования в сущность Booking.
     *
     * @param dto    DTO бронирования
     * @param item   объект вещи, которую бронируют
     * @param booker пользователь, который бронирует
     * @return объект {@link Booking}
     */
    public static Booking toBooking(BookingDto dto, Item item, User booker) {
        return new Booking(
                dto.getId(),
                item,
                booker,
                dto.getStart(),
                dto.getEnd(),
                dto.getStatus()
        );
    }

    /** Приватный конструктор для запрета создания экземпляров класса. */
    private BookingMapper() {}
}
