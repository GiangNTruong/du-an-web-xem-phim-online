package com.ra.hn_jv231229_md03_watchfilmonline_project.model.request;

public class UserUpdateStatusRequest {
    private Long userId;
    private Boolean newStatus;

    public UserUpdateStatusRequest(Long userId, Boolean newStatus) {
        this.userId = userId;
        this.newStatus = newStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Boolean newStatus) {
        this.newStatus = newStatus;
    }

    @Override
    public String toString() {
        return "UserUpdateStatusRequest{" +
                "userId=" + userId +
                ", newStatus=" + newStatus +
                '}';
    }
}

