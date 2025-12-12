package ru.practicum.shareit.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для краткой информации о бронировании.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingInfoDto {
    /** Идентификатор бронирования */
    private Long id;

    /** Идентификатор пользователя, который забронировал */
    private Long bookerId;
}
