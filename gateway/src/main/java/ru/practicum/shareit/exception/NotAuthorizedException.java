package ru.practicum.shareit.exception;

public class NotAuthorizedException extends RuntimeException {

    public NotAuthorizedException(final String message) {
        super(message);
    }
}
