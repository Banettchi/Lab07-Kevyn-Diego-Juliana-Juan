package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.dto.auth.AuthResponse;
import edu.eci.dosw.tech_cup.dto.auth.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    private AuthService authService;

    @BeforeEach
    void setUp() {
        authService = new AuthService();
    }

    @Test
    void debeAutenticarConCredencialesValidas() {
        LoginRequest request = new LoginRequest("admin@techcup.com", "admin123");
        AuthResponse response = authService.login(request);

        assertTrue(response.isSuccess());
        assertEquals("ADMIN", response.getRole());
    }

    @Test
    void debeFallarConContrasenaIncorrecta() {
        LoginRequest request = new LoginRequest("admin@techcup.com", "wrongpass");
        AuthResponse response = authService.login(request);

        assertFalse(response.isSuccess());
        assertNull(response.getRole());
    }

    @Test
    void debeFallarConCorreoInexistente() {
        LoginRequest request = new LoginRequest("noexiste@techcup.com", "pass123");
        AuthResponse response = authService.login(request);

        assertFalse(response.isSuccess());
    }

    @Test
    void debeAutenticarJugador() {
        LoginRequest request = new LoginRequest("jugador@techcup.com", "jugador123");
        AuthResponse response = authService.login(request);

        assertTrue(response.isSuccess());
        assertEquals("PLAYER", response.getRole());
    }
}