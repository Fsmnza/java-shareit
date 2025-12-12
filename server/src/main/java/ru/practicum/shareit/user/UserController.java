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

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable final Long id) {
        return userService.getById(id);
    }

    @PostMapping
    public UserDto createUser(@RequestBody final UserDto dto) {
        return userService.createUser(dto);
    }

    @PatchMapping("/{id}")
    public UserDto updateUser(@RequestBody final UserDto user,
                              @PathVariable final Long id) {
        return userService.updateUserById(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable final Long id) {
        userService.removeNyId(id);
    }
}
