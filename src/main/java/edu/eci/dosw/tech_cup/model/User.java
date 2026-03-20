package edu.eci.dosw.tech_cup.model;

import edu.eci.dosw.tech_cup.model.enums.ParticipantType;
import edu.eci.dosw.tech_cup.model.enums.Role;

public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private ParticipantType participantType;
    private String jerseyNumber;
    private String position;
    private String photoUrl;
    private boolean available;
    private Team team;

    public User() {
    }

    public User(Long id, String name, String email, String password, Role role,
                ParticipantType participantType, String jerseyNumber,
                String position, String photoUrl, boolean available) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.participantType = participantType;
        this.jerseyNumber = jerseyNumber;
        this.position = position;
        this.photoUrl = photoUrl;
        this.available = available;
    }

    public void register() {
        System.out.println(name + " has been registered successfully.");
    }

    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    public void updateProfile(String jerseyNumber, String position, String photoUrl) {
        this.jerseyNumber = jerseyNumber;
        this.position = position;
        this.photoUrl = photoUrl;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void joinTeam(Team team) {
        this.team = team;
        this.available = false;
    }

    public void leaveTeam() {
        this.team = null;
        this.available = true;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public ParticipantType getParticipantType() {
        return participantType;
    }

    public String getJerseyNumber() {
        return jerseyNumber;
    }

    public String getPosition() {
        return position;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public boolean isAvailable() {
        return available;
    }

    public Team getTeam() {
        return team;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setParticipantType(ParticipantType participantType) {
        this.participantType = participantType;
    }

    public void setJerseyNumber(String jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}