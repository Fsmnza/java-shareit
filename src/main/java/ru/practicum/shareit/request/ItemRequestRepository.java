package ru.practicum.shareit.request;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ItemRequestRepository {
    private final List<ItemRequest> requests = new ArrayList<>();
    private Long nextId = 1L;

    public ItemRequest createRequest(ItemRequest request) {
        request.setId(nextId++);
        requests.add(request);
        return request;
    }

    public List<ItemRequest> getAllRequests() {
        return new ArrayList<>(requests);
    }
}
