package com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class UserDto
{
    private Long userId;
    private String userName;
    private String fullName;
    private String email;
    private String phone;
    private String role;
    private Date createdAt;
    private Boolean status;
    private MultipartFile fileAvatar;
    private String avatarUrl;


    public UserDto(String avatarUrl, Date createdAt, String email, MultipartFile fileAvatar, String fullName, String phone, String role, Boolean status, Long userId, String userName)
    {
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
        this.email = email;
        this.fileAvatar = fileAvatar;
        this.fullName = fullName;
        this.phone = phone;
        this.role = role;
        this.status = status;
        this.userId = userId;
        this.userName = userName;
    }

    public UserDto(Long userId, String username, String email, String phone, String name, Date createdAt, Boolean status)
    {

        this.userId = userId;
        this.userName = username;
        this.email = email;
        this.phone = phone;
        this.role = name;
        this.createdAt = createdAt;
        this.status = status;
    }

    public UserDto(Long userId, String fullName, String email, String phone, String avatarUrl) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.avatarUrl = avatarUrl;
    }

    public UserDto(Long userId, String fullName, String email, String phone, MultipartFile fileAvatar) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.fileAvatar = fileAvatar;
    }

    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
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
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    public Boolean getStatus() {
        return status;
    }
    
    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    public MultipartFile getFileAvatar() {
        return fileAvatar;
    }
    
    public void setFileAvatar(MultipartFile fileAvatar) {
        this.fileAvatar = fileAvatar;
    }
    
    public String getAvatarUrl() {
        return avatarUrl;
    }
    
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    
    @Override
    public String toString()
    {
        return "UserDto{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                ", createdAt=" + createdAt +
                ", status=" + status +
                '}';
    }
}
