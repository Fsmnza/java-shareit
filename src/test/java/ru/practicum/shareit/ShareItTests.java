package ru.practicum.shareit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.service.BookingServiceImpl;
import ru.practicum.shareit.booking.BookingRepository;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.user.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(BookingServiceImpl.class)
class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingServiceImpl bookingService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void contextLoads() {
        // Проверяем, что Spring поднял бин BookingRepository
        assertThat(bookingRepository).isNotNull();
        assertThat(bookingService).isNotNull();
    }

    @Test
    void testSaveAndFindBooking() {
        // Просто пример: создаем объект Booking и сохраняем
        Booking booking = new Booking();
        // Тут можно задать минимум полей: start, end, item, booker
        // booking.setStart(...);
        // booking.setEnd(...);
        // booking.setItem(...);
        // booking.setBooker(...);
        bookingRepository.save(booking);

        assertThat(bookingRepository.findById(booking.getId())).isPresent();
    }
}
