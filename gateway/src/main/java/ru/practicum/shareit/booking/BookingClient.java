package ru.practicum.shareit.booking;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.common.BaseClient;

import java.util.Map;

/**
 * Клиент для взаимодействия с сервисом бронирований.
 */
@Service
public class BookingClient extends BaseClient {

    public BookingClient(@Value("${shareIt-server.url}") String serverUrl,
                         RestTemplateBuilder builder) {
        super(builder.uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + "/bookings"))
                .build());
    }

    /**
     * Создать новое бронирование.
     *
     * @param userId ID пользователя
     * @param dto    данные бронирования
     * @return ответ сервера
     */
    public ResponseEntity<Object> createBooking(Long userId, BookingDto dto) {
        return post("", userId, dto);
    }

    /**
     * Одобрить или отклонить бронирование.
     *
     * @param ownerId   ID владельца вещи
     * @param bookingId ID бронирования
     * @param approved  true — одобрено, false — отклонено
     * @return ответ сервера
     */
    public ResponseEntity<Object> approveBooking(Long ownerId, Long bookingId, boolean approved) {
        Map<String, Object> params = Map.of("approved", approved);
        return patch("/" + bookingId + "?approved={approved}", ownerId, params, null);
    }

    /**
     * Получить информацию о бронировании.
     *
     * @param userId    ID пользователя
     * @param bookingId ID бронирования
     * @return ответ сервера
     */
    public ResponseEntity<Object> getBooking(Long userId, Long bookingId) {
        return get("/" + bookingId, userId);
    }

    /**
     * Получить бронирования пользователя по статусу.
     *
     * @param userId ID пользователя
     * @param state  статус бронирований
     * @return ответ сервера
     */
    public ResponseEntity<Object> getBookingsByBooker(Long userId, String state) {
        Map<String, Object> params = Map.of("state", state);
        return get("?state={state}", userId, params);
    }

    /**
     * Получить бронирования вещей владельца по статусу.
     *
     * @param userId ID владельца
     * @param state  статус бронирований
     * @return ответ сервера
     */
    public ResponseEntity<Object> getBookingsForOwner(Long userId, String state) {
        Map<String, Object> params = Map.of("state", state);
        return get("/owner?state={state}", userId, params);
    }
}
