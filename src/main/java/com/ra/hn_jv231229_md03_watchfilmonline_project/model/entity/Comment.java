package com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;
    @Column(name = "stars")
    private Integer stars;
    @Column(name = "content")
    private String content;
    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "film_id")
    private Film film;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public Comment()
    {
    }

    public Comment(Long commentId, Integer stars, String content, Film film, User user)
    {
        this.commentId = commentId;
        this.stars = stars;
        this.content = content;
        this.film = film;
        this.user = user;
    }

    public Long getCommentId()
    {
        return commentId;
    }

    public void setCommentId(Long commentId)
    {
        this.commentId = commentId;
    }

    public Integer getStars()
    {
        return stars;
    }

    public void setStars(Integer stars)
    {
        this.stars = stars;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Film getFilm()
    {
        return film;
    }

    public void setFilm(Film film)
    {
        this.film = film;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
