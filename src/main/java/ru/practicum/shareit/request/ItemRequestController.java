package ru.practicum.shareit.request;

import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.service.ItemRequestService;

import java.util.List;

@RestController
@RequestMapping(path = "/requests")
public class ItemRequestController {

    private final ItemRequestService service;

    public ItemRequestController(ItemRequestService service) {
        this.service = service;
    }

    @PostMapping
    public ItemRequest createRequest(@RequestBody ItemRequestDto dto,
                                     @RequestHeader("X-Sharer-User-Id") long userId) {
        ItemRequest request = new ItemRequest();
        request.setDescription(dto.getDescription());
        request.setRequestId(userId);
        return service.createRequest(request);
    }

    @GetMapping
    public List<ItemRequest> getAllRequests() {
        return service.getAllRequests();
    }
}
