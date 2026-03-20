package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.model.Tournament;
import edu.eci.dosw.tech_cup.model.enums.TournamentStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TournamentService {

    private final List<Tournament> tournaments = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public TournamentService() {
        Tournament t1 = new Tournament();
        t1.setId(idGenerator.getAndIncrement());
        t1.setName("TechCup 2025");
        t1.setTeamLimit(8);
        t1.setTeamCost(50000);
        t1.setStatus(TournamentStatus.DRAFT);

        Tournament t2 = new Tournament();
        t2.setId(idGenerator.getAndIncrement());
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

    public Optional<Tournament> findById(Long id) {
        return tournaments.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    public Tournament create(Tournament tournament) {
        tournament.setId(idGenerator.getAndIncrement());
        tournament.setStatus(TournamentStatus.DRAFT);
        tournaments.add(tournament);
        return tournament;
    }

    public Optional<Tournament> update(Long id, Tournament updated) {
        for (int i = 0; i < tournaments.size(); i++) {
            Tournament current = tournaments.get(i);
            if (current.getId().equals(id)) {
                if (current.getStatus() == TournamentStatus.FINISHED) {
                    return Optional.empty();
                }
                updated.setId(id);
                updated.setStatus(current.getStatus());
                tournaments.set(i, updated);
                return Optional.of(updated);
            }
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        return tournaments.removeIf(t ->
                t.getId().equals(id) && t.getStatus() == TournamentStatus.DRAFT);
    }
}