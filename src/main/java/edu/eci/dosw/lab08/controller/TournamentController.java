package edu.eci.dosw.lab08.controller;

import edu.eci.dosw.lab08.model.Tournament;
import edu.eci.dosw.lab08.model.enums.TournamentStatus;
import edu.eci.dosw.lab08.service.TournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    // GET /api/tournaments
    @GetMapping
    public ResponseEntity<List<Tournament>> getAll() {
        return ResponseEntity.ok(tournamentService.findAll());
    }

    // GET /api/tournaments/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getById(@PathVariable Long id) {
        return tournamentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // POST /api/tournaments
    @PostMapping
    public ResponseEntity<Tournament> create(@RequestBody Tournament tournament) {
        Tournament created = tournamentService.create(tournament);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/tournaments/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id,
                                         @RequestBody Tournament tournament) {
        // Verificar si existe y si está FINISHED antes de intentar actualizar
        return tournamentService.findById(id)
                .map(existing -> {
                    if (existing.getStatus() == TournamentStatus.FINISHED) {
                        return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body((Object) "No se puede modificar un torneo finalizado");
                    }
                    return tournamentService.update(id, tournament)
                            .map(updated -> ResponseEntity.ok((Object) updated))
                            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // DELETE /api/tournaments/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return tournamentService.findById(id)
                .map(existing -> {
                    if (existing.getStatus() != TournamentStatus.DRAFT) {
                        return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body((Object) "Solo se puede eliminar un torneo en estado Borrador");
                    }
                    tournamentService.delete(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
