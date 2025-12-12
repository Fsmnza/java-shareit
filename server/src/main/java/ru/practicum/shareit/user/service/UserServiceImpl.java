package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserDto;
import ru.practicum.shareit.user.UserRepository;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.exception.DuplicatedDataException;
import ru.practicum.shareit.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(final long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден: " + id));
        return UserMapper.toUserDto(user);
    }

    @Override
    @Transactional
    public UserDto removeNyId(final long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден: " + id));
        userRepository.deleteById(id);
        return UserMapper.toUserDto(user);
    }

    @Override
    @Transactional
    public UserDto createUser(final UserDto dto) {
        userRepository.findByEmail(dto.getEmail()).ifPresent(u -> {
            throw new DuplicatedDataException("Email уже используется: " + dto.getEmail());
        });

        User user = UserMapper.toUser(dto);
        user = userRepository.save(user);
        return UserMapper.toUserDto(user);
    }

    @Override
    @Transactional
    public UserDto updateUserById(final UserDto dto, final long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден: " + id));
        if (dto.getName() != null) {
            user.setName(dto.getName());
        }
        if (dto.getEmail() != null) {
            userRepository.findByEmail(dto.getEmail()).ifPresent(existing -> {
                if (!existing.getId().equals(id)) {
                    throw new DuplicatedDataException("Email уже используется другим пользователем: "
                                                      + dto.getEmail());
                }
            });
            user.setEmail(dto.getEmail());
        }
        user = userRepository.save(user);
        return UserMapper.toUserDto(user);
    }
}