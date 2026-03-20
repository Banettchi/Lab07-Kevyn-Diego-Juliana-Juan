package edu.eci.dosw.tech_cup.controller;

import edu.eci.dosw.tech_cup.model.Tournament;
import edu.eci.dosw.tech_cup.model.enums.TournamentStatus;
import edu.eci.dosw.tech_cup.service.TournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
@Tag(name = "Torneos", description = "Operaciones relacionadas con torneos")
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los torneos")
    public ResponseEntity<List<Tournament>> getAll() {
        return ResponseEntity.ok(tournamentService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener torneo por ID")
    public ResponseEntity<Tournament> getById(@PathVariable Long id) {
        Tournament tournament = tournamentService.findById(id);
        if (tournament != null) {
            return ResponseEntity.ok(tournament);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    @Operation(summary = "Crear torneo", description = "Se crea en estado DRAFT por defecto")
    public ResponseEntity<Tournament> create(@RequestBody Tournament tournament) {
        Tournament created = tournamentService.create(tournament);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar torneo", description = "No aplica si está FINISHED")
    public ResponseEntity<Object> update(@PathVariable Long id,
                                         @RequestBody Tournament tournament) {
        Tournament existing = tournamentService.findById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (existing.getStatus() == TournamentStatus.FINISHED) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede modificar un torneo finalizado");
        }
        Tournament updated = tournamentService.update(id, tournament);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar torneo", description = "Solo si está en estado DRAFT")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Tournament existing = tournamentService.findById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (existing.getStatus() != TournamentStatus.DRAFT) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Solo se puede eliminar un torneo en estado Borrador");
        }
        tournamentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
