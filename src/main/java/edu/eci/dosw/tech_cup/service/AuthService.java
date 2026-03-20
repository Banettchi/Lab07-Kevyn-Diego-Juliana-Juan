package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.dto.auth.AuthResponse;
import edu.eci.dosw.tech_cup.dto.auth.LoginRequest;
import edu.eci.dosw.tech_cup.model.User;
import edu.eci.dosw.tech_cup.model.enums.Role;
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
        for (User u : users) {
            if (u.getEmail().equals(request.getEmail())
                    && u.getPassword().equals(request.getPassword())) {
                return new AuthResponse(true, "Login exitoso", u.getRole().name());
            }
        }
        return new AuthResponse(false, "Credenciales inválidas", null);
    }
}