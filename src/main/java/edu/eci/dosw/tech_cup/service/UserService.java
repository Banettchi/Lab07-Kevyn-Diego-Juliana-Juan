package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.model.User;
import edu.eci.dosw.tech_cup.model.enums.Role;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public UserService() {
        User u1 = new User();
        u1.setId(idGenerator.getAndIncrement());
        u1.setName("Jugador Demo");
        u1.setEmail("jugador@techcup.com");
        u1.setPassword("jugador123");
        u1.setRole(Role.PLAYER);
        u1.setAvailable(true);

        User u2 = new User();
        u2.setId(idGenerator.getAndIncrement());
        u2.setName("Admin TechCup");
        u2.setEmail("admin@techcup.com");
        u2.setPassword("admin123");
        u2.setRole(Role.ADMIN);
        u2.setAvailable(true);

        users.add(u1);
        users.add(u2);
    }

    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    public User create(User user) {
        user.setId(idGenerator.getAndIncrement());
        user.setRole(Role.PLAYER);
        user.setAvailable(true);
        users.add(user);
        return user;
    }

    public Optional<User> update(Long id, User updated) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                updated.setId(id);
                updated.setRole(users.get(i).getRole());
                users.set(i, updated);
                return Optional.of(updated);
            }
        }
        return Optional.empty();
    }

    public Optional<User> assignRole(Long id, Role role) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .map(u -> {
                    u.setRole(role);
                    return u;
                });
    }

    public Optional<User> deactivate(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .map(u -> {
                    u.setAvailable(false);
                    return u;
                });
    }
}