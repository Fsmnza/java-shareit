package ru.practicum.shareit.booking.model;

import lombok.Data;
import ru.practicum.shareit.item.model.Item;

import java.time.LocalDateTime;

@Data
public class Booking {
    private Long id;
    private Item item;
    private Long bookerId;
    private LocalDateTime start;
    private LocalDateTime end;
}
