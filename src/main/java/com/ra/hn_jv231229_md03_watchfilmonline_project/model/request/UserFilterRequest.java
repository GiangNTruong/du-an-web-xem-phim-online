package com.ra.hn_jv231229_md03_watchfilmonline_project.model.request;

import lombok.Data;

@Data
public class UserFilterRequest {
    private String email;
    private String phone;
    private String role;
    private String username;
    private String fullName;
    private Boolean status;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "UserFilterRequest{" +
                "email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", status=" + status +
                '}';
    }
}
