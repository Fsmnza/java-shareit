package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserRepository;
import ru.practicum.shareit.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public final class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(final Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Пользователь не найден: " + id));
        return UserMapper.toUserDto(user);
    }

    @Override
    public void removeNyId(final Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("Пользователь не найден: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserDto createUser(final UserDto userDto) {
        User user = UserMapper.toUser(userDto);
        User savedUser = userRepository.save(user);
        return UserMapper.toUserDto(savedUser);
    }

    @Override
    public UserDto updateUserById(final Long id, final UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Пользователь не найден: " + id));
        if (userDto.getName() != null) {
            user.setName(userDto.getName());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        User updatedUser = userRepository.save(user);
        return UserMapper.toUserDto(updatedUser);
    }
}
