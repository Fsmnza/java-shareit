package ru.practicum.shareit.booking.dto.mapper;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

public class BookingMapper {

    public static Booking toBooking(BookingDto dto, Item item, User booker) {
        if (dto == null) {
            return null;
        }

        Booking booking = new Booking();
        booking.setId(dto.getId());
        booking.setItem(item);
        booking.setBookingUser(booker);
        booking.setStart(dto.getStart());
        booking.setEnd(dto.getEnd());
        booking.setStatus(dto.getStatus());

        return booking;
    }
}