package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getById(long id);

    UserDto removeNyId(long id);

    UserDto createUser(UserDto user);

    UserDto updateUserById(UserDto user, long id);
}
