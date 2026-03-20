package edu.eci.dosw.tech_cup.dto.auth;

public class AuthResponse {

    private boolean success;
    private String message;
    private String role;

    public AuthResponse() {}

    public AuthResponse(boolean success, String message, String role) {
        this.success = success;
        this.message = message;
        this.role = role;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public String getRole() { return role; }
    public void setSuccess(boolean success) { this.success = success; }
    public void setMessage(String message) { this.message = message; }
    public void setRole(String role) { this.role = role; }
}