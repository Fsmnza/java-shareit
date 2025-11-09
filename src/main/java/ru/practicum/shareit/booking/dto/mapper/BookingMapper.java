package ru.practicum.shareit.booking.dto.mapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.booking.model.Booking;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookingMapper implements RowMapper<Booking> {
    @Override
    public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
