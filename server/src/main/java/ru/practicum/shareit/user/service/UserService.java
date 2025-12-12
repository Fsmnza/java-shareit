package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.UserDto;

import java.util.List;

public interface UserService {

    /**
     * Получить список всех пользователей.
     *
     * @return список пользователей
     */
    List<UserDto> getAllUsers();

    /**
     * Получить пользователя по идентификатору.
     *
     * @param id идентификатор пользователя
     * @return пользователь
     */
    UserDto getById(final Long id);

    /**
     * Удалить пользователя по идентификатору.
     *
     * @param id идентификатор пользователя
     */
    void removeNyId(final Long id);

    /**
     * Создать нового пользователя.
     *
     * @param userDto данные пользователя
     * @return созданный пользователь
     */
    UserDto createUser(final UserDto userDto);

    /**
     * Обновить пользователя по идентификатору.
     *
     * @param id идентификатор пользователя
     * @param userDto новые данные пользователя
     * @return обновлённый пользователь
     */
    UserDto updateUserById(final Long id, final UserDto userDto);
}
