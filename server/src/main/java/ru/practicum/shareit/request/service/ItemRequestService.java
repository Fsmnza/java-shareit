package ru.practicum.shareit.request.service;

import ru.practicum.shareit.request.dto.ItemRequestDto;

import java.util.List;

public interface ItemRequestService {
    ItemRequestDto createRequest(final Long userId, final ItemRequestDto dto);

    List<ItemRequestDto> getRequestsByUser(final Long userId);

    List<ItemRequestDto> getAllRequests(final Long userId);

    ItemRequestDto getRequestById(final Long userId, final Long requestId);
}
