package edu.eci.dosw.lab08.service;

import edu.eci.dosw.lab08.dto.auth.AuthResponse;
import edu.eci.dosw.lab08.dto.auth.LoginRequest;
import edu.eci.dosw.lab08.model.User;
import edu.eci.dosw.lab08.model.enums.Role;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {

    private final List<User> users = new ArrayList<>();

    public AuthService() {
        User admin = new User();
        admin.setEmail("admin@techcup.com");
        admin.setPassword("admin123");
        admin.setRole(Role.ADMIN);
        admin.setName("Admin TechCup");

        User player = new User();
        player.setEmail("jugador@techcup.com");
        player.setPassword("jugador123");
        player.setRole(Role.PLAYER);
        player.setName("Jugador Demo");

        users.add(admin);
        users.add(player);
    }

    public AuthResponse login(LoginRequest request) {
        return users.stream()
                .filter(u -> u.getEmail().equals(request.getEmail())
                        && u.getPassword().equals(request.getPassword()))
                .findFirst()
                .map(u -> new AuthResponse(true, "Login exitoso", u.getRole().name()))
                .orElse(new AuthResponse(false, "Credenciales inválidas", null));
    }
}