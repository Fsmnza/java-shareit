package ru.practicum.shareit.user;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private Long nextId = 1L;
    private final List<User> users = new ArrayList<>();

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public User createNewUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if (user.getEmail().equals(u.getEmail())) {
                throw new RuntimeException("такая почта уже существует");
            }
        }
        user.setId(nextId++);
        users.add(user);
        return user;
    }

    public User getById(long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("пользователь не найден"));
    }


    public User deleteUserById(long id) {
        User user = getById(id);
        users.remove(user);
        return user;
    }


    public User updateUserById(User updateUser, long id) {
        User oldUser = getById(id);
        if (updateUser.getEmail() != null) {
            boolean emailExists = users.stream()
                    .anyMatch(u -> !u.getId().equals(id) && u.getEmail().equalsIgnoreCase(updateUser.getEmail()));
            if (emailExists) {
                throw new RuntimeException("Email already exists");
            }
            oldUser.setEmail(updateUser.getEmail());
        }

        if (updateUser.getName() != null) {
            oldUser.setName(updateUser.getName());
        }
        return oldUser;
    }

    public boolean existsById(long id) {
        return users.stream().anyMatch(u -> u.getId() == id);
    }

}

