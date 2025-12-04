package ru.practicum.shareit.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.booking.dto.BookingInfoDto;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Builder
@Data
public class ItemDto {
    private Long id;

    @NotBlank(groups = OnCreate.class)
    @NotNull(groups = OnCreate.class)
    private String name;

    @NotBlank(groups = OnCreate.class)
    @NotNull(groups = OnCreate.class)
    private String description;

    @JsonProperty(required = true)
    @NotNull(groups = OnCreate.class)
    private Boolean available;

    private Long ownerId;
    private Long requestId;
    private BookingInfoDto lastBooking;
    private BookingInfoDto nextBooking;
    private List<CommentDto> comments;

    public interface OnCreate {}
}