package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserRepository;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.mapper.UserMapper;

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
    public UserDto removeNyId(long id) {
        return UserMapper.toDto(userRepository.deleteUserById(id));
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.toUser(userDto);
        User created = userRepository.createNewUser(user);
        return UserMapper.toDto(created);
    }

    @Override
    public UserDto updateUserById(UserDto userDto, long id) {
        User user = UserMapper.toUser(userDto);
        User updated = userRepository.updateUserById(user, id);
        return UserMapper.toDto(updated);
    }
}
