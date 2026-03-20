package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.model.Tournament;
import edu.eci.dosw.tech_cup.model.enums.TournamentStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TournamentService {

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
        return new ArrayList<>(tournaments);
    }

    public Tournament findById(Long id) {
        for (Tournament t : tournaments) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    public Tournament create(Tournament tournament) {
        tournament.setId(nextId++);
        tournament.setStatus(TournamentStatus.DRAFT);
        tournaments.add(tournament);
        return tournament;
    }

    public Tournament update(Long id, Tournament updated) {
        for (int i = 0; i < tournaments.size(); i++) {
            Tournament current = tournaments.get(i);
            if (current.getId().equals(id)) {
                if (current.getStatus() == TournamentStatus.FINISHED) {
                    return null;
                }
                updated.setId(id);
                updated.setStatus(current.getStatus());
                tournaments.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    public boolean delete(Long id) {
        for (int i = 0; i < tournaments.size(); i++) {
            if (tournaments.get(i).getId().equals(id)) {
                if (tournaments.get(i).getStatus() == TournamentStatus.DRAFT) {
                    tournaments.remove(i);
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}
