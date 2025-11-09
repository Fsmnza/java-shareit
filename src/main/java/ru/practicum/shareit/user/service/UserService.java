package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    User getById(long id);

    User removeNyId(long id);

    User createUser(User user);

    User updateUserById(User user, long id);
}
