package ru.practicum.shareit.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.BookingRepository;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.mapper.BookingMapper;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository repository;
    private final ItemRepository itemRepository;

    public BookingDto createBooking(BookingDto bookingDto, long userId) {
        Booking booking = new Booking();
        booking.setBookerId(userId);
        booking.setStart(bookingDto.getStart());
        booking.setEnd(bookingDto.getEnd());
        booking.setItem(itemRepository.getById(bookingDto.getItemId()));
        Booking created = repository.createBooking(booking);
        return BookingMapper.toDto(created);
    }

    public List<BookingDto> getAllBookings() {
        return repository.getAllBookings().stream()
                .map(BookingMapper::toDto)
                .toList();
    }
}
