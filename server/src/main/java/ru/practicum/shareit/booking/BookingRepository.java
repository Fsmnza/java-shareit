package ru.practicum.shareit.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.booking.Booking;
import ru.practicum.shareit.booking.Status;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByBookingUserIdOrderByStartDesc(Long bookerId);

    List<Booking> findAllByItemOwnerIdOrderByStartDesc(Long ownerId);

    List<Booking> findByBookingUserIdAndStatusOrderByStartDesc(Long bookerId, Status status);

    List<Booking> findByItemOwnerIdAndStatusOrderByStartDesc(Long ownerId, Status status);

    @Query("SELECT b FROM Booking b WHERE b.bookingUser.id = :userId AND b.start <= CURRENT_TIMESTAMP" +
           " AND b.end >= CURRENT_TIMESTAMP ORDER BY b.start DESC")
    List<Booking> findCurrentByBooker(@Param("userId") Long userId);

    @Query("SELECT b FROM Booking b WHERE b.bookingUser.id = :userId AND b.end < CURRENT_TIMESTAMP " +
           "ORDER BY b.start DESC")
    List<Booking> findPastByBooker(@Param("userId") Long userId);

    @Query("SELECT b FROM Booking b WHERE b.bookingUser.id = :userId AND b.start > CURRENT_TIMESTAMP " +
           "ORDER BY b.start DESC")
    List<Booking> findFutureByBooker(@Param("userId") Long userId);

    @Query("SELECT b FROM Booking b WHERE b.item.owner.id = :ownerId AND b.start " +
           "<= CURRENT_TIMESTAMP AND b.end >= CURRENT_TIMESTAMP ORDER BY b.start DESC")
    List<Booking> findCurrentByOwner(@Param("ownerId") Long ownerId);

    @Query("SELECT b FROM Booking b WHERE b.item.owner.id = :ownerId AND b.end < CURRENT_TIMESTAMP " +
           "ORDER BY b.start DESC")
    List<Booking> findPastByOwner(@Param("ownerId") Long ownerId);

    @Query("SELECT b FROM Booking b WHERE b.item.owner.id = :ownerId AND b.start > CURRENT_TIMESTAMP " +
           "ORDER BY b.start DESC")
    List<Booking> findFutureByOwner(@Param("ownerId") Long ownerId);

    boolean existsByItemIdAndBookingUserIdAndStatusAndEndBefore(Long itemId, Long bookerId,
                                                                Status status, LocalDateTime dateTime);

    List<Booking> findByItemIdInAndStatus(List<Long> itemIds, Status status);

}
