package edu.eci.dosw.tech_cup.model;

import edu.eci.dosw.tech_cup.model.enums.Role;
import edu.eci.dosw.tech_cup.model.enums.TeamStatus;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private Long id;
    private String name;
    private String badgeUrl;
    private String uniformColor;
    private TeamStatus status;
    private User captain;
    private List<User> players;
    private Tournament tournament;
    private Payment payment;

    public Team() {
        this.players = new ArrayList<>();
        this.status = TeamStatus.PENDING_REGISTRATION;
    }

    public Team(Long id, String name, String badgeUrl, String uniformColor, User captain) {
        this.id = id;
        this.name = name;
        this.badgeUrl = badgeUrl;
        this.uniformColor = uniformColor;
        this.captain = captain;
        this.players = new ArrayList<>();
        this.status = TeamStatus.PENDING_REGISTRATION;
    }

    public void createTeam() {
        System.out.println("Team " + name + " created successfully.");
    }

    public boolean addPlayer(User player) {
        if (player == null) {
            return false;
        }

        if (players.size() >= 12) {
            System.out.println("Maximum number of players reached.");
            return false;
        }

        if (player.getTeam() != null) {
            System.out.println("Player already belongs to another team.");
            return false;
        }

        players.add(player);
        player.joinTeam(this);
        return true;
    }

    public boolean removePlayer(User player) {
        if (players.remove(player)) {
            player.leaveTeam();
            return true;
        }
        return false;
    }

    public boolean validatePlayerCount() {
        return players.size() >= 7 && players.size() <= 12;
    }

    public void assignCaptain(User captain) {
        this.captain = captain;
        if (captain != null) {
            captain.setRole(Role.CAPTAIN);
        }
    }

    public int getPlayerCount() {
        return players.size();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public String getUniformColor() {
        return uniformColor;
    }

    public TeamStatus getStatus() {
        return status;
    }

    public User getCaptain() {
        return captain;
    }

    public List<User> getPlayers() {
        return players;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    public void setUniformColor(String uniformColor) {
        this.uniformColor = uniformColor;
    }

    public void setStatus(TeamStatus status) {
        this.status = status;
    }

    public void setCaptain(User captain) {
        this.captain = captain;
    }

    public void setPlayers(List<User> players) {
        this.players = players;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}