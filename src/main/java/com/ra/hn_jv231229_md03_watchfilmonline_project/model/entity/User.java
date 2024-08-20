package com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.constant.UserRole;

import org.hibernate.annotations.Check;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.EqualsAndHashCode;
import lombok.ToString;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
public class User
{
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "username", unique = true)
    @NotEmpty(message = "Tên người dùng không được để trống")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "phone", unique = true)
    @NotEmpty(message = "Vui lòng nhập số điện thoại")
    private String phone;
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;
    @Column(name = "fullname")
    @NotEmpty(message = "Vui lòng nhập tên của bạn")
    private String fullname;
    @Column(name = "wallet_balance")
    @Min(0)
    private Long wallet_balance = 0L;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "avatar")
    private String avatar="https://www.shutterstock.com/image-vector/default-avatar-profile-icon-social-600nw-1677509740.jpg";
    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt = new Date();
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "updated_at")
    private Date updatedAt = new Date();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "film_favorite", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Film> filmSet;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "history", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "film_episode_id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<FilmEpisode> filmEpisodeSet;


    public User()
    {
    }

    public User(Long userId)
    {
        this.userId = userId;
    }

    public User(String avatar, Date createdAt, String email, Set<FilmEpisode> filmEpisodeSet, Set<Film> filmSet, String fullname, String password, String phone, Boolean status, Date updatedAt, Long userId, String username, UserRole userRole, Long wallet_balance)
    {
        this.avatar = avatar;
        this.createdAt = createdAt;
        this.email = email;
        this.filmEpisodeSet = filmEpisodeSet;
        this.filmSet = filmSet;
        this.fullname = fullname;
        this.password = password;
        this.phone = phone;
        this.status = status;
        this.updatedAt = updatedAt;
        this.userId = userId;
        this.username = username;
        this.userRole = userRole;
        this.wallet_balance = wallet_balance;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public UserRole getUserRole()
    {
        return userRole;
    }

    public void setUserRole(UserRole userRole)
    {
        this.userRole = userRole;
    }

    public String getFullname()
    {
        return fullname;
    }

    public void setFullname(String fullname)
    {
        this.fullname = fullname;
    }

    public Long getWallet_balance()
    {
        return wallet_balance;
    }

    public void setWallet_balance(Long wallet_balance)
    {
        this.wallet_balance = wallet_balance;
    }

    public Boolean getStatus()
    {
        return status;
    }

    public void setStatus(Boolean status)
    {
        this.status = status;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public Set<Film> getFilmSet()
    {
        return filmSet;
    }

    public void setFilmSet(Set<Film> filmSet)
    {
        this.filmSet = filmSet;
    }

    public Set<FilmEpisode> getFilmEpisodeSet()
    {
        return filmEpisodeSet;
    }

    public void setFilmEpisodeSet(Set<FilmEpisode> filmEpisodeSet)
    {
        this.filmEpisodeSet = filmEpisodeSet;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "userId=" + userId +
                ", userName='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                ", fullName='" + fullname + '\'' +
                ", wallet_balance=" + wallet_balance +
                ", status=" + status +
                ", avatar='" + avatar + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
