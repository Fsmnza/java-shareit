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

    /** ID бронирования. */
    private Long id;

    /** ID пользователя, сделавшего бронирование. */
    private Long bookerId;
}
