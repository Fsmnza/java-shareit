package ru.practicum.shareit.booking.service;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.BookingRepository;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingResponseDto;
import ru.practicum.shareit.booking.dto.mapper.BookingMapper;
import ru.practicum.shareit.booking.dto.mapper.BookingResponseMapper;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingState;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserRepository;
import ru.practicum.shareit.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    public BookingResponseDto createBooking(Long userId, BookingDto dto) {
        User booker = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден: " + userId));
        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new NotFoundException("Вещь не найдена: " + dto.getItemId()));

        if (!Boolean.TRUE.equals(item.getAvailable())) {
            throw new ValidationException("Вещь недоступна для бронирования");
        }
        if (dto.getStart() == null || dto.getEnd() == null || !dto.getEnd().isAfter(dto.getStart())) {
            throw new ValidationException("Некорректный диапазон дат бронирования");
        }
        if (item.getOwner().getId().equals(userId)) {
            throw new ValidationException("Владелец не может бронировать собственную вещь");
        }

        Booking booking = BookingMapper.toBooking(dto, item, booker);
        booking.setStatus(Status.WAITING);
        booking = bookingRepository.save(booking);
        return BookingResponseMapper.toBookingResponseDto(booking);
    }

    @Override
    public BookingResponseDto approveBooking(Long ownerId, Long bookingId, boolean approved) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Бронирование не найдено: " + bookingId));

        if (!booking.getItem().getOwner().getId().equals(ownerId)) {
            throw new ValidationException("Только владелец может подтверждать бронирование");
        }
        if (booking.getStatus() == Status.APPROVED && approved) {
            throw new ValidationException("Бронирование уже подтверждено");
        }

        booking.setStatus(approved ? Status.APPROVED : Status.REJECTED);
        booking = bookingRepository.save(booking);
        return BookingResponseMapper.toBookingResponseDto(booking);
    }

    @Override
    public BookingResponseDto getBookingById(Long userId, Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Бронирование не найдено: " + bookingId));

        if (!booking.getBookingUser().getId().equals(userId) &&
            !booking.getItem().getOwner().getId().equals(userId)) {
            throw new ValidationException("Доступ запрещён к данному бронированию");
        }

        return BookingResponseMapper.toBookingResponseDto(booking);
    }

    @Override
    public List<BookingResponseDto> getBookingsByBooker(Long userId, BookingState state) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("Пользователь не найден: " + userId);
        }

        List<Booking> bookings;
        switch (state) {
            case CURRENT -> bookings = bookingRepository.findCurrentByBooker(userId);
            case PAST -> bookings = bookingRepository.findPastByBooker(userId);
            case FUTURE -> bookings = bookingRepository.findFutureByBooker(userId);
            case WAITING ->
                    bookings = bookingRepository.findByBookingUserIdAndStatusOrderByStartDesc(userId, Status.WAITING);
            case REJECTED ->
                    bookings = bookingRepository.findByBookingUserIdAndStatusOrderByStartDesc(userId, Status.REJECTED);
            default -> bookings = bookingRepository.findAllByBookingUserIdOrderByStartDesc(userId);
        }

        return bookings.stream()
                .map(BookingResponseMapper::toBookingResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingResponseDto> getBookingsForOwner(Long userId, BookingState state) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("Пользователь не найден: " + userId);
        }

        List<Booking> bookings;
        switch (state) {
            case CURRENT -> bookings = bookingRepository.findCurrentByOwner(userId);
            case PAST -> bookings = bookingRepository.findPastByOwner(userId);
            case FUTURE -> bookings = bookingRepository.findFutureByOwner(userId);
            case WAITING ->
                    bookings = bookingRepository.findByItemOwnerIdAndStatusOrderByStartDesc(userId, Status.WAITING);
            case REJECTED ->
                    bookings = bookingRepository.findByItemOwnerIdAndStatusOrderByStartDesc(userId, Status.REJECTED);
            default -> bookings = bookingRepository.findAllByItemOwnerIdOrderByStartDesc(userId);
        }

        return bookings.stream()
                .map(BookingResponseMapper::toBookingResponseDto)
                .collect(Collectors.toList());
    }
}
