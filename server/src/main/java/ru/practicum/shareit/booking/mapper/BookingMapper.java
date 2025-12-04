package ru.practicum.shareit.booking.mapper;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.Booking;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

public class BookingMapper {
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
}