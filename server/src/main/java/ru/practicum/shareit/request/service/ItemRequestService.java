package ru.practicum.shareit.request.service;

import ru.practicum.shareit.request.dto.ItemRequestDto;

import java.util.List;

public interface ItemRequestService {

    /**
     * Создать новый запрос на вещь.
     *
     * @param userId идентификатор пользователя
     * @param dto данные запроса
     * @return созданный запрос
     */
    ItemRequestDto createRequest(final Long userId, final ItemRequestDto dto);

    /**
     * Получить список запросов пользователя.
     *
     * @param userId идентификатор пользователя
     * @return список запросов
     */
    List<ItemRequestDto> getRequestsByUser(final Long userId);

    /**
     * Получить список всех запросов, кроме запросов пользователя.
     *
     * @param userId идентификатор пользователя
     * @return список запросов
     */
    List<ItemRequestDto> getAllRequests(final Long userId);

    /**
     * Получить запрос по идентификатору.
     *
     * @param userId идентификатор пользователя
     * @param requestId идентификатор запроса
     * @return запрос
     */
    ItemRequestDto getRequestById(final Long userId, final Long requestId);
}
