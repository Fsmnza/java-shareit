package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.getAllUsers();
    }

    @Override
    public User getById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public User removeNyId(long id) {
        return userRepository.deleteUserById(id);
    }

    @Override
    public User createUser(User user) {
        return userRepository.createNewUser(user);
    }

    @Override
    public User updateUserById(User user, long id) {
        return userRepository.updateUserById(user, id);
    }
}
