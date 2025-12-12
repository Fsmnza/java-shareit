package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public final class UserController {

    private final UserService userService;

    /**
     * Получить список всех пользователей.
     *
     * @return список пользователей
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Получить пользователя по идентификатору.
     *
     * @param id идентификатор пользователя
     * @return пользователь
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable final Long id) {
        return userService.getById(id);
    }

    /**
     * Создать нового пользователя.
     *
     * @param dto данные пользователя
     * @return созданный пользователь
     */
    @PostMapping
    public UserDto createUser(@RequestBody final UserDto dto) {
        return userService.createUser(dto);
    }

    /**
     * Обновить пользователя по идентификатору.
     *
     * @param user новые данные пользователя
     * @param id идентификатор пользователя
     * @return обновлённый пользователь
     */
    @PatchMapping("/{id}")
    public UserDto updateUser(@RequestBody final UserDto user,
                              @PathVariable final Long id) {
        return userService.updateUserById(id, user);
    }

    /**
     * Удалить пользователя по идентификатору.
     *
     * @param id идентификатор пользователя
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable final Long id) {
        userService.removeNyId(id);
    }
}
