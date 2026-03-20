package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.model.Tournament;
import edu.eci.dosw.tech_cup.model.enums.TournamentStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TournamentService {

    private static final Logger log = LoggerFactory.getLogger(TournamentService.class);
    private final List<Tournament> tournaments = new ArrayList<>();
    private Long nextId = 1L;

    public TournamentService() {
        Tournament t1 = new Tournament();
        t1.setId(nextId++);
        t1.setName("TechCup 2025");
        t1.setTeamLimit(8);
        t1.setTeamCost(50000);
        t1.setStatus(TournamentStatus.DRAFT);

        Tournament t2 = new Tournament();
        t2.setId(nextId++);
        t2.setName("TechCup Verano");
        t2.setTeamLimit(16);
        t2.setTeamCost(80000);
        t2.setStatus(TournamentStatus.ACTIVE);

        tournaments.add(t1);
        tournaments.add(t2);
    }

    public List<Tournament> findAll() {
        log.debug("Obteniendo todos los torneos");
        return new ArrayList<>(tournaments);
    }

    public Tournament findById(Long id) {
        log.debug("Buscando torneo con id: {}", id);
        for (Tournament t : tournaments) {
            if (t.getId().equals(id)) {
                log.info("Torneo encontrado con id: {}", id);
                return t;
            }
        }
        log.warn("Torneo no encontrado con id: {}", id);
        return null;
    }

    public Tournament create(Tournament tournament) {
        log.debug("Creando torneo: {}", tournament.getName());
        tournament.setId(nextId++);
        tournament.setStatus(TournamentStatus.DRAFT);
        tournaments.add(tournament);
        log.info("Torneo creado con id: {}", tournament.getId());
        return tournament;
    }

    public Tournament update(Long id, Tournament updated) {
        log.debug("Actualizando torneo con id: {}", id);
        for (int i = 0; i < tournaments.size(); i++) {
            Tournament current = tournaments.get(i);
            if (current.getId().equals(id)) {
                if (current.getStatus() == TournamentStatus.FINISHED) {
                    log.warn("No se puede modificar torneo finalizado con id: {}", id);
                    return null;
                }
                updated.setId(id);
                updated.setStatus(current.getStatus());
                tournaments.set(i, updated);
                log.info("Torneo actualizado con id: {}", id);
                return updated;
            }
        }
        log.warn("Torneo no encontrado para actualizar con id: {}", id);
        return null;
    }

    public boolean delete(Long id) {
        log.debug("Eliminando torneo con id: {}", id);
        for (int i = 0; i < tournaments.size(); i++) {
            if (tournaments.get(i).getId().equals(id)) {
                if (tournaments.get(i).getStatus() == TournamentStatus.DRAFT) {
                    tournaments.remove(i);
                    log.info("Torneo eliminado con id: {}", id);
                    return true;
                }
                log.warn("No se puede eliminar torneo que no está en DRAFT con id: {}", id);
                return false;
            }
        }
        log.warn("Torneo no encontrado para eliminar con id: {}", id);
        return false;
    }
}