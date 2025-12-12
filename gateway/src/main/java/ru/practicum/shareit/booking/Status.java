package ru.practicum.shareit.booking;

/**
 * Статусы бронирования.
 */
public enum Status {
    /** Бронирование ожидает подтверждения. */
    WAITING,
    /** Бронирование подтверждено. */
    APPROVED,
    /** Бронирование отклонено. */
    REJECTED,
    /** Бронирование отменено. */
    CANCELED
}
