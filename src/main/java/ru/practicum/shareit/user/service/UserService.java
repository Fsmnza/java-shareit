package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    User getById(long id);

    UserDto removeNyId(long id);

    UserDto createUser(UserDto user);

    UserDto updateUserById(UserDto user, long id);
}
