package ru.practicum.shareit.item.dto.mapper;

import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.item.model.Item;

import java.time.LocalDateTime;

public class CommentMapper {
    public static CommentDto toDto(Comment comment) {
        if (comment == null) return null;

        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .authorName(comment.getUser() != null ? comment.getUser().getName() : null)
                .created(comment.getCreatedDay())
                .build();
    }

    public static Comment toComment(CommentDto dto, User user, Item item) {
        if (dto == null) return null;

        return Comment.builder()
                .id(dto.getId())
                .text(dto.getText())
                .user(user)
                .item(item)
                .createdDay(LocalDateTime.now())
                .build();
    }
}