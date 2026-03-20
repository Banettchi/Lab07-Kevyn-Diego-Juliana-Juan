package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.model.Tournament;
import edu.eci.dosw.tech_cup.model.enums.TournamentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TournamentServiceTest {

    private TournamentService tournamentService;

    @BeforeEach
    void setUp() {
        tournamentService = new TournamentService();
    }

    @Test
    void debeCrearTorneoConEstadoDraft() {
        Tournament t = new Tournament();
        t.setName("TechCup 2025");
        t.setTeamLimit(8);
        t.setTeamCost(50000);

        Tournament created = tournamentService.create(t);

        assertNotNull(created);
        assertEquals("TechCup 2025", created.getName());
        assertEquals(TournamentStatus.DRAFT, created.getStatus());
    }

    @Test
    void noDebeModificarTorneoFinalizado() {
        Tournament t = new Tournament();
        t.setName("Torneo Finalizado");
        t.setTeamLimit(8);
        Tournament created = tournamentService.create(t);
        created.setStatus(TournamentStatus.FINISHED);

        Tournament updated = new Tournament();
        updated.setName("Nombre Nuevo");
        Tournament result = tournamentService.update(created.getId(), updated);

        assertNull(result);
    }

    @Test
    void debeEliminarTorneoDraft() {
        Tournament t = new Tournament();
        t.setName("Torneo Borrador");
        Tournament created = tournamentService.create(t);

        boolean deleted = tournamentService.delete(created.getId());

        assertTrue(deleted);
    }

    @Test
    void noDebeEliminarTorneoActivo() {
        Tournament t = new Tournament();
        t.setName("Torneo Activo");
        Tournament created = tournamentService.create(t);
        created.setStatus(TournamentStatus.ACTIVE);

        boolean deleted = tournamentService.delete(created.getId());

        assertFalse(deleted);
    }
}
