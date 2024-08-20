package com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "film_episode")
public class FilmEpisode
{
    @Id
    @Column(name = "film_episode_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long filmEpisodeId;
    @Column(name = "episode_number")
    private Integer episodeNumber;
    @Column(name = "film_episode_url")
    private String filmEpisodeUrl;
    @Column(name = "filme_episode_image")
    private String filmEpisodeImage;
    @Column(name = "episode_time")
    private Integer episodeTime;
    @Column(name = "film_current_time")
    private Integer filmCurrentTime;
    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "film_id")
    private Film film;

    public FilmEpisode()
    {
    }

    public FilmEpisode(Integer episodeNumber, Integer episodeTime, Film film, Integer filmCurrentTime, Long filmEpisodeId, String filmEpisodeImage, String filmEpisodeUrl)
    {
        this.episodeNumber = episodeNumber;
        this.episodeTime = episodeTime;
        this.film = film;
        this.filmCurrentTime = filmCurrentTime;
        this.filmEpisodeId = filmEpisodeId;
        this.filmEpisodeImage = filmEpisodeImage;
        this.filmEpisodeUrl = filmEpisodeUrl;
    }

    public Integer getEpisodeNumber()
    {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber)
    {
        this.episodeNumber = episodeNumber;
    }

    public Film getFilm()
    {
        return film;
    }

    public void setFilm(Film film)
    {
        this.film = film;
    }

    public Integer getEpisodeTime()
    {
        return episodeTime;
    }

    public String getFilmEpisodeImage()
    {
        return filmEpisodeImage;
    }

    public void setFilmEpisodeImage(String filmEpisodeImage)
    {
        this.filmEpisodeImage = filmEpisodeImage;
    }

    public void setEpisodeTime(Integer episodeTime)
    {
        this.episodeTime = episodeTime;
    }

    public Integer getFilmCurrentTime()
    {
        return filmCurrentTime;
    }

    public void setFilmCurrentTime(Integer filmCurrentTime)
    {
        this.filmCurrentTime = filmCurrentTime;
    }

    public Long getFilmEpisodeId()
    {
        return filmEpisodeId;
    }

    public void setFilmEpisodeId(Long filmEpisodeId)
    {
        this.filmEpisodeId = filmEpisodeId;
    }

    public String getFilmEpisodeUrl()
    {
        return filmEpisodeUrl;
    }

    public void setFilmEpisodeUrl(String filmEpisodeUrl)
    {
        this.filmEpisodeUrl = filmEpisodeUrl;
    }
}
