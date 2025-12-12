package ru.practicum.shareit.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.booking.Status;

import java.time.LocalDateTime;

/**
 * DTO для создания или передачи информации о бронировании.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    /** Идентификатор бронирования */
    private Long id;

    /** Идентификатор вещи, которую бронируют */
    private Long itemId;

    /** Идентификатор пользователя, который бронирует */
    private Long bookerId;

    /** Дата и время начала бронирования */
    private LocalDateTime start;

    /** Дата и время окончания бронирования */
    private LocalDateTime end;

    /** Статус бронирования */
    private Status status;
}
