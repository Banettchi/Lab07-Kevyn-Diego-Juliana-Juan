package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.model.User;
import edu.eci.dosw.tech_cup.model.enums.Role;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final List<User> users = new ArrayList<>();
    private Long nextId = 1L;

    public UserService() {
        User u1 = new User();
        u1.setId(nextId++);
        u1.setName("Jugador Demo");
        u1.setEmail("jugador@techcup.com");
        u1.setPassword("jugador123");
        u1.setRole(Role.PLAYER);
        u1.setAvailable(true);

        User u2 = new User();
        u2.setId(nextId++);
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

    public User findById(Long id) {
        log.debug("Buscando usuario con id: {}", id);
        for (User u : users) {
            if (u.getId().equals(id)) {
                log.info("Usuario encontrado con id: {}", id);
                return u;
            }
        }
        log.warn("Usuario no encontrado con id: {}", id);
        return null;
    }

    public User create(User user) {
        log.debug("Creando usuario: {}", user.getEmail());
        user.setId(nextId++);
        user.setRole(Role.PLAYER);
        user.setAvailable(true);
        users.add(user);
        log.info("Usuario creado con id: {}", user.getId());
        return user;
    }

    public User update(Long id, User updated) {
        log.debug("Actualizando usuario con id: {}", id);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                updated.setId(id);
                updated.setRole(users.get(i).getRole());
                users.set(i, updated);
                log.info("Usuario actualizado con id: {}", id);
                return updated;
            }
        }
        log.warn("Usuario no encontrado para actualizar con id: {}", id);
        return null;
    }

    public User assignRole(Long id, Role role) {
        log.debug("Asignando rol {} a usuario con id: {}", role, id);
        for (User u : users) {
            if (u.getId().equals(id)) {
                u.setRole(role);
                log.info("Rol {} asignado a usuario con id: {}", role, id);
                return u;
            }
        }
        log.warn("Usuario no encontrado para asignar rol con id: {}", id);
        return null;
    }

    public User deactivate(Long id) {
        log.debug("Inactivando usuario con id: {}", id);
        for (User u : users) {
            if (u.getId().equals(id)) {
                u.setAvailable(false);
                log.info("Usuario inactivado con id: {}", id);
                return u;
            }
        }
        log.warn("Usuario no encontrado para inactivar con id: {}", id);
        return null;
    }
}