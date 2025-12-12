package ru.practicum.shareit.exception;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public final class ErrorHandler {

    /**
     * @param e исключение NotFoundException
     * @return сообщение об ошибке
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNotFound(final NotFoundException e) {
        log.error("Не найдено: {}", e.getMessage());
        return Map.of("error", e.getMessage());
    }

    /**
     * @param e исключение NotAuthorizedException
     * @return сообщение об ошибке
     */
    @ExceptionHandler(NotAuthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, String> handleNotAuthorized(final NotAuthorizedException e) {
        return Map.of("error", e.getMessage());
    }

    /**
     * @param e исключение ValidationException
     * @return сообщение об ошибке
     */
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidation(final ValidationException e) {
        return Map.of("error", e.getMessage());
    }

    /**
     * @param e исключение MethodArgumentNotValidException
     * @return сообщение с указанием поля и ошибки
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleMethodArgumentNotValid(final MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Ошибка валидации");
        return Map.of("error", message);
    }

    /**
     * @param e исключение IllegalArgumentException
     * @return сообщение об ошибке
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleIllegalArgument(final IllegalArgumentException e) {
        return Map.of("error", e.getMessage());
    }

    /**
     * @param e общее исключение
     * @return стандартное сообщение об ошибке сервера
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleException(final Exception e) {
        log.error("Внутренняя ошибка сервера", e);
        return Map.of("error", "Внутренняя ошибка сервера");
    }
}
