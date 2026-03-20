package edu.eci.dosw.tech_cup.model;

import edu.eci.dosw.tech_cup.model.enums.MatchPhase;

import java.time.LocalDateTime;

public class Match {

    private Long id;
    private LocalDateTime matchDateTime;
    private String field;
    private int homeScore;
    private int awayScore;
    private MatchPhase phase;
    private Team homeTeam;
    private Team awayTeam;
    private Tournament tournament;

    public Match() {
    }

    public Match(Long id, LocalDateTime matchDateTime, String field, MatchPhase phase,
                 Team homeTeam, Team awayTeam, Tournament tournament) {
        this.id = id;
        this.matchDateTime = matchDateTime;
        this.field = field;
        this.phase = phase;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.tournament = tournament;
    }

    public void scheduleMatch() {
        System.out.println("Match scheduled successfully.");
    }

    public void registerResult(int homeScore, int awayScore) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getMatchDateTime() {
        return matchDateTime;
    }

    public String getField() {
        return field;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public MatchPhase getPhase() {
        return phase;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMatchDateTime(LocalDateTime matchDateTime) {
        this.matchDateTime = matchDateTime;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public void setPhase(MatchPhase phase) {
        this.phase = phase;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
}