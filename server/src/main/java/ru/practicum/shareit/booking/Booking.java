package ru.practicum.shareit.booking;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

import java.time.LocalDateTime;

/**
 * Сущность бронирования.
 */
@Entity
@Table(name = "booking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    /** Идентификатор бронирования */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Вещь, которую бронируют */
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    /** Пользователь, который сделал бронирование */
    @ManyToOne
    @JoinColumn(name = "booker_id", nullable = false)
    private User bookingUser;

    /** Дата и время начала бронирования */
    @Column(name = "start_date", nullable = false)
    private LocalDateTime start;

    /** Дата и время окончания бронирования */
    @Column(name = "end_date", nullable = false)
    private LocalDateTime end;

    /** Статус бронирования */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}
