package ru.practicum.shareit.request.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.request.ItemRequest;
import ru.practicum.shareit.request.ItemRequestRepository;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.mapper.ItemRequestMapper;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserRepository;
import ru.practicum.shareit.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemRequestImpl implements ItemRequestService {
    private final ItemRequestRepository requestRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public ItemRequestDto createRequest(Long userId, ItemRequestDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден: " + userId));
        if (dto.getDescription() == null || dto.getDescription().isBlank()) {
            throw new IllegalArgumentException("Описание запроса не может быть пустым");
        }
        ItemRequest request = new ItemRequest();
        request.setDescription(dto.getDescription());
        request.setRequester(user);
        request.setCreated(LocalDateTime.now());
        request = requestRepository.save(request);
        return ItemRequestMapper.toDto(request, List.of());
    }

    @Override
    public List<ItemRequestDto> getRequestsByUser(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден: " + userId));
        return requestRepository.findAllByRequesterIdOrderByCreatedDesc(userId).stream()
                .map(req -> {
                    List<Item> items = itemRepository.findByRequestId(req.getId());
//                    if (items == null) {
//                        items = new ArrayList<>();
//                    }
                    return ItemRequestMapper.toDto(req, items);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemRequestDto> getAllRequests(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден: " + userId));

        return requestRepository.findAllByRequesterIdNotOrderByCreatedDesc(userId).stream()
                .map(req -> {
                    List<Item> items = itemRepository.findByRequestId(req.getId());
                    return ItemRequestMapper.toDto(req, items);
                })
                .collect(Collectors.toList());
    }

    @Override
    public ItemRequestDto getRequestById(Long userId, Long requestId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден: " + userId));
        ItemRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Запрос не найден: " + requestId));
        List<Item> items = itemRepository.findByRequestId(requestId);
        return ItemRequestMapper.toDto(request, items);
    }
}