package ru.practicum.shareit.exception;
//@param
public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException(final String message) {
        super(message);
    }
}
