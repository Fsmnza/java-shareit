package ru.practicum.shareit.request.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.ItemRequest;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.dto.ItemRequestResponseDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ItemRequestMapper {
    private static final Logger log = LoggerFactory.getLogger(ItemRequestMapper.class);

    public static ItemRequestDto toDto(ItemRequest request, List<Item> items) {
        log.info("Mapping ItemRequest to ItemRequestDto. Request: {}, Items: {}", request, items);

        if (request == null) {
            log.warn("Request is null. Returning null.");
            return null;
        }

        List<ItemRequestResponseDto> responses = items == null ? List.of() :
                items.stream()
                        .filter(Objects::nonNull)
                        .map(item -> {
                            Long ownerId = (item.getOwner() != null) ? item.getOwner().getId() : null;
                            log.debug("Mapping item: {} with ownerId: {}", item, ownerId);
                            return new ItemRequestResponseDto(item.getId(), item.getName(), ownerId);
                        })
                        .collect(Collectors.toList());

        ItemRequestDto dto = ItemRequestDto.builder()
                .id(request.getId())
                .description(request.getDescription())
                .requestorId(request.getRequester() != null ? request.getRequester().getId() : null)
                .created(request.getCreated())
                .items(responses)
                .build();

        log.info("Successfully mapped ItemRequest to ItemRequestDto: {}", dto);
        return dto;
    }
}