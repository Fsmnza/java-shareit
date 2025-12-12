package ru.practicum.shareit.exception;

public class DuplicatedDataException extends RuntimeException {
    /**
     * @param message лублирующий объект
     */
    public DuplicatedDataException(String message) {
        super(message);
    }
}
