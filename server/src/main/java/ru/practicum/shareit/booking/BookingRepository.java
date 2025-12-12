package ru.practicum.shareit.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    /**
     * Найти все бронирования пользователя, отсортированные по дате начала (убывание).
     */
    List<Booking> findAllByBookingUserIdOrderByStartDesc(Long bookerId);

    /**
     * Найти все бронирования вещей владельца, отсортированные по дате начала (убывание).
     */
    List<Booking> findAllByItemOwnerIdOrderByStartDesc(Long ownerId);

    /**
     * Найти бронирования пользователя по статусу, отсортированные по дате начала (убывание).
     */
    List<Booking> findByBookingUserIdAndStatusOrderByStartDesc(Long bookerId, Status status);

    /**
     * Найти бронирования владельца по статусу, отсортированные по дате начала (убывание).
     */
    List<Booking> findByItemOwnerIdAndStatusOrderByStartDesc(Long ownerId, Status status);

    /**
     * Найти текущие бронирования пользователя (сейчас активные).
     */
    @Query("SELECT b FROM Booking b WHERE b.bookingUser.id = :userId AND " +
           "b.start <= CURRENT_TIMESTAMP AND b.end >= CURRENT_TIMESTAMP ORDER BY b.start DESC")
    List<Booking> findCurrentByBooker(@Param("userId") Long userId);

    /**
     * Найти прошедшие бронирования пользователя (с датой окончания в прошлом).
     */
    @Query("SELECT b FROM Booking b WHERE b.bookingUser.id = :userId" +
           " AND b.end < CURRENT_TIMESTAMP " +
           "ORDER BY b.start DESC")
    List<Booking> findPastByBooker(@Param("userId") Long userId);

    /**
     * Найти будущие бронирования пользователя (с датой начала в будущем).
     */
    @Query("SELECT b FROM Booking b WHERE b.bookingUser.id = :userId" +
           " AND b.start > CURRENT_TIMESTAMP " +
           "ORDER BY b.start DESC")
    List<Booking> findFutureByBooker(@Param("userId") Long userId);

    /**
     * Найти текущие бронирования для вещей владельца.
     */
    @Query("SELECT b FROM Booking b WHERE b.item.owner.id = :ownerId " +
           "AND b.start <= CURRENT_TIMESTAMP AND b.end >= CURRENT_TIMESTAMP ORDER BY b.start DESC")
    List<Booking> findCurrentByOwner(@Param("ownerId") Long ownerId);

    /**
     * Найти прошедшие бронирования для вещей владельца.
     */
    @Query("SELECT b FROM Booking b WHERE b.item.owner.id = :ownerId" +
           " AND b.end < CURRENT_TIMESTAMP " +
           "ORDER BY b.start DESC")
    List<Booking> findPastByOwner(@Param("ownerId") Long ownerId);

    /**
     * Найти будущие бронирования для вещей владельца.
     */
    @Query("SELECT b FROM Booking b WHERE b.item.owner.id = :ownerId " +
           "AND b.start > CURRENT_TIMESTAMP " +
           "ORDER BY b.start DESC")
    List<Booking> findFutureByOwner(@Param("ownerId") Long ownerId);

    /**
     * Проверить, существует ли завершённое бронирование для конкретного пользователя, вещи и статуса.
     */
    boolean existsByItemIdAndBookingUserIdAndStatusAndEndBefore(Long itemId, Long bookerId,
                                                                Status status, LocalDateTime dateTime);

    /**
     * Найти бронирования для списка вещей с определённым статусом.
     */
    List<Booking> findByItemIdInAndStatus(List<Long> itemIds, Status status);
}
