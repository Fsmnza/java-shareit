package ru.practicum.shareit.exception;

public class NotFoundException extends RuntimeException {
    /**
     * @param message Не найдено
     */
    public NotFoundException(String message) {
        super(message);
    }
}
