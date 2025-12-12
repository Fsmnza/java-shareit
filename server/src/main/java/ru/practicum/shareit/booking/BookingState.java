package ru.practicum.shareit.booking;

/**
 * Возможные состояния бронирования.
 */
public enum BookingState {
    ALL,
    CURRENT,
    PAST,
    FUTURE,
    WAITING,
    REJECTED;

    /**
     * Преобразует строку в значение BookingState.
     * Если переданное значение некорректное или null, возвращается ALL.
     *
     * @param state строковое представление состояния
     * @return объект {@link BookingState}
     */
    public static BookingState from(String state) {
        try {
            return BookingState.valueOf(state.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return ALL;
        }
    }
}
