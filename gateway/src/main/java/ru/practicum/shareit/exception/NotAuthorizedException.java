package ru.practicum.shareit.exception;

public class NotAuthorizedException extends RuntimeException {

    /**
     * @param message Ошибка Не авторизован
     */
    public NotAuthorizedException(final String message) {
        super(message);
    }
}
