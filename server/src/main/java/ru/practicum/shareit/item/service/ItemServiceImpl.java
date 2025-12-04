package ru.practicum.shareit.item.service;

import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.BookingRepository;
import ru.practicum.shareit.booking.Booking;
import ru.practicum.shareit.booking.Status;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.mapper.CommentMapper;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.repository.CommentRepository;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserRepository;
import ru.practicum.shareit.exception.NotAuthorizedException;
import ru.practicum.shareit.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final CommentRepository commentRepository;

    @Override
    public ItemDto createItem(Long userId, ItemDto itemDto) {
        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));

        Item item = ItemMapper.toItem(itemDto, owner, null);
        item = itemRepository.save(item);
        return ItemMapper.toItemDto(item);
    }

    @Override
    @Transactional
    public ItemDto updateItem(Long userId, Long itemId, ItemDto dto) {
        System.out.println("=== UPDATE ITEM ===");
        System.out.println("userId: " + userId);
        System.out.println("itemId: " + itemId);
        System.out.println("dto.name: " + dto.getName());
        System.out.println("dto.description: " + dto.getDescription());
        System.out.println("dto.available: " + dto.getAvailable());

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Вещь не найдена: " + itemId));

        System.out.println("Before update - item.name: " + item.getName());
        System.out.println("Before update - item.description: " + item.getDescription());
        System.out.println("Before update - item.available: " + item.getAvailable());
        if (!item.getOwner().getId().equals(userId)) {
            throw new NotAuthorizedException("Только владелец может редактировать вещь");
        }
        if (dto.getName() != null) {
            item.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            item.setDescription(dto.getDescription());
        }
        if (dto.getAvailable() != null) {
            item.setAvailable(dto.getAvailable());
        }
        Item savedItem = itemRepository.save(item);
        ItemDto result = ItemMapper.toItemDto(savedItem);
        return result;
    }

    @Override
    public ItemDto getItemById(Long userId, Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Вещь не найдена: " + itemId));

        List<Comment> comments = commentRepository.findByItemId(itemId);
        return ItemMapper.toItemDto(item, null, null, comments);
    }

    @Override
    public List<ItemDto> getItemsByOwner(Long userId) {
        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден: " + userId));
        List<Item> items = itemRepository.findByOwnerId(owner.getId());
        List<Long> itemIds = items.stream().map(Item::getId).toList();
        List<Comment> allComments = commentRepository.findByItemIdIn(itemIds);
        Map<Long, List<Comment>> commentsMap = allComments.stream()
                .collect(Collectors.groupingBy(c -> c.getItem().getId()));
        List<Booking> allBookings = bookingRepository.findByItemIdInAndStatus(itemIds, Status.APPROVED);
        Map<Long, List<Booking>> bookingsMap = allBookings.stream()
                .collect(Collectors.groupingBy(b -> b.getItem().getId()));
        return items.stream()
                .map(item -> {
                    List<Comment> comments = commentsMap.getOrDefault(item.getId(), List.of());
                    List<Booking> bookings = bookingsMap.getOrDefault(item.getId(), List.of());
                    Booking lastBooking = bookings.stream()
                            .filter(b -> b.getStart().isBefore(LocalDateTime.now()))
                            .max(Comparator.comparing(Booking::getStart))
                            .orElse(null);
                    Booking nextBooking = bookings.stream()
                            .filter(b -> b.getStart().isAfter(LocalDateTime.now()))
                            .min(Comparator.comparing(Booking::getStart))
                            .orElse(null);
                    return ItemMapper.toItemDto(item, lastBooking, nextBooking, comments);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> searchItems(String text) {
        if (text == null || text.isBlank()) {
            return List.of();
        }

        return itemRepository.searchAvailableByText(text.toLowerCase()).stream()
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CommentDto addComment(Long userId, Long itemId, CommentDto commentDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден: " + userId));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Вещь не найдена " + itemId));

        boolean hasBooking = bookingRepository.existsByItemIdAndBookingUserIdAndStatusAndEndBefore(
                itemId, userId, Status.APPROVED, LocalDateTime.now()
        );

        if (!hasBooking) {
            throw new ValidationException("Комментирование разрешено только после окончания бронирования");
        }

        Comment comment = CommentMapper.toComment(commentDto, user, item);
        comment = commentRepository.save(comment);
        return CommentMapper.toDto(comment);
    }
}