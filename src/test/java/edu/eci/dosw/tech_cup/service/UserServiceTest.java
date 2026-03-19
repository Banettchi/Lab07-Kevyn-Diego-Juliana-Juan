package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.model.User;
import edu.eci.dosw.tech_cup.model.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void debeCrearUsuarioConRolPlayer() {
        User u = new User();
        u.setName("Juan");
        u.setEmail("juan@techcup.com");
        u.setPassword("pass123");

        User created = userService.create(u);

        assertNotNull(created);
        assertEquals("Juan", created.getName());
        assertEquals(Role.PLAYER, created.getRole());
        assertTrue(created.isAvailable());
    }

    @Test
    void debeInactivarUsuario() {
        User u = new User();
        u.setName("Pedro");
        u.setEmail("pedro@techcup.com");
        u.setPassword("pass123");
        User created = userService.create(u);

        var result = userService.deactivate(created.getId());

        assertTrue(result.isPresent());
        assertFalse(result.get().isAvailable());
    }

    @Test
    void debeAsignarRolCorrectamente() {
        User u = new User();
        u.setName("Ana");
        u.setEmail("ana@techcup.com");
        u.setPassword("pass123");
        User created = userService.create(u);

        var result = userService.assignRole(created.getId(), Role.ADMIN);

        assertTrue(result.isPresent());
        assertEquals(Role.ADMIN, result.get().getRole());
    }

    @Test
    void debeRetornarVacioSiUsuarioNoExiste() {
        var result = userService.findById(999L);
        assertTrue(result.isEmpty());
    }
}