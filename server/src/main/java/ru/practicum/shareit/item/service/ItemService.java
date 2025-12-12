package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.exception.NotAuthorizedException;

import java.util.List;
public interface ItemService {

    /**
     * Создать новую вещь.
     *
     * @param userId идентификатор пользователя
     * @param dto данные вещи
     * @return созданная вещь
     */
    ItemDto createItem(final Long userId, final ItemDto dto);

    /**
     * Обновить вещь.
     *
     * @param userId идентификатор пользователя
     * @param itemId идентификатор вещи
     * @param dto данные для обновления
     * @return обновленная вещь
     * @throws NotAuthorizedException если пользователь не владелец вещи
     */
    ItemDto updateItem(final Long userId, final Long itemId, final ItemDto dto)
            throws NotAuthorizedException;

    /**
     * Получить вещь по идентификатору.
     *
     * @param userId идентификатор пользователя
     * @param itemId идентификатор вещи
     * @return вещь
     */
    ItemDto getItemById(final Long userId, final Long itemId);

    /**
     * Получить все вещи владельца.
     *
     * @param userId идентификатор владельца
     * @return список вещей
     */
    List<ItemDto> getItemsByOwner(final Long userId);

    /**
     * Поиск вещей по тексту.
     *
     * @param text текст для поиска
     * @return список вещей
     */
    List<ItemDto> searchItems(final String text);

    /**
     * Добавить комментарий к вещи.
     *
     * @param userId идентификатор пользователя
     * @param itemId идентификатор вещи
     * @param commentDto данные комментария
     * @return добавленный комментарий
     */
    CommentDto addComment(final Long userId, final Long itemId, final CommentDto commentDto);
}
