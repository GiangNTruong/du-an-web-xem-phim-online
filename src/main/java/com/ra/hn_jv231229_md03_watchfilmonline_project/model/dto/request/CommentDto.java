package com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request;

public class CommentDto {
    private Integer stars;
    private String content;
    private Long filmId;
    private Long userId;

    public CommentDto() {
    }

    public CommentDto(Integer stars, String content, Long filmId, Long userId) {
        this.stars = stars;
        this.content = content;
        this.filmId = filmId;
        this.userId = userId;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
