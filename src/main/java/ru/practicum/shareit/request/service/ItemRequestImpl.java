package ru.practicum.shareit.request.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.request.ItemRequest;
import ru.practicum.shareit.request.ItemRequestRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ItemRequestImpl implements ItemRequestService {
   private final ItemRequestRepository repository;

   public ItemRequest createRequest(ItemRequest request) {
      return repository.createRequest(request);
   }

   public List<ItemRequest> getAllRequests() {
      return repository.getAllRequests();
   }
}
