package ru.practicum.shareit.exception;

public class NotAuthorizedException extends RuntimeException {
    /**
     * @param message Ошибка Не авторизован
     */
    public NotAuthorizedException(String message) {
        super(message);
    }
}
