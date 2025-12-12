package ru.practicum.shareit.booking.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.booking.Status;

import java.time.LocalDateTime;

/**
 * DTO для создания и передачи данных о бронировании.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDto {

    /** ID бронирования. */
    private Long id;

    /** ID вещи для бронирования. */
    @NotNull(message = "ID вещи не может быть пустым")
    @Positive(message = "ID вещи должно быть положительным числом")
    private Long itemId;

    /** Дата и время начала бронирования. */
    @NotNull(message = "Дата начала бронирования обязательна")
    private LocalDateTime start;

    /** Дата и время окончания бронирования. */
    @NotNull(message = "Дата окончания бронирования обязательна")
    @FutureOrPresent(message = "Дата окончания бронирования должна быть в будущем")
    private LocalDateTime end;

    /** Статус бронирования. */
    private Status status;
}
