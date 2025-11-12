package ru.practicum.shareit.request.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.request.ItemRequest;
import ru.practicum.shareit.request.ItemRequestRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemRequestImpl implements ItemRequestService {
    private final ItemRequestRepository repository;

    @Override
    public ItemRequest createRequest(ItemRequest request) {
        request.setCreated(LocalDateTime.now());
        return repository.save(request);
    }

    @Override
    public List<ItemRequest> getAllRequests() {
        return repository.findAll();
    }
}
