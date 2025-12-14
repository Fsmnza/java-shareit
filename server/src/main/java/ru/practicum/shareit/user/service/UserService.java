package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getById(final Long id);

    void removeNyId(final Long id);

    UserDto createUser(final UserDto userDto);

    UserDto updateUserById(final Long id, final UserDto userDto);
}
