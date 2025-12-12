package ru.practicum.shareit.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.booking.Status;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.UserDto;

import java.time.LocalDateTime;

/**
 * DTO для ответа с полной информацией о бронировании.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponseDto {
    /** Идентификатор бронирования */
    private Long id;

    /** Информация о вещи */
    private ItemDto item;

    /** Информация о пользователе, который бронирует */
    private UserDto booker;

    /** Дата и время начала бронирования */
    private LocalDateTime start;

    /** Дата и время окончания бронирования */
    private LocalDateTime end;

    /** Статус бронирования */
    private Status status;
}
