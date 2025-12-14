package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.exception.NotAuthorizedException;

import java.util.List;

public interface ItemService {
    ItemDto createItem(final Long userId, final ItemDto dto);

    ItemDto updateItem(final Long userId, final Long itemId, final ItemDto dto)
            throws NotAuthorizedException;

    ItemDto getItemById(final Long userId, final Long itemId);

    List<ItemDto> getItemsByOwner(final Long userId);

    List<ItemDto> searchItems(final String text);

    CommentDto addComment(final Long userId, final Long itemId, final CommentDto commentDto);
}
