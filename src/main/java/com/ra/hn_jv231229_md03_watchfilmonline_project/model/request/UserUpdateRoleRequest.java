package com.ra.hn_jv231229_md03_watchfilmonline_project.model.request;

public class UserUpdateRoleRequest {
    private Long userId;
    private String newRole;

    public UserUpdateRoleRequest(Long userId, String newRole) {
        this.userId = userId;
        this.newRole = newRole;
    }

    public String getNewRole() {
        return newRole;
    }

    public void setNewRole(String newRole) {
        this.newRole = newRole;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}

