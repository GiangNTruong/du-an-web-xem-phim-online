package com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class FilmEpisodeDto
{
    private Long filmEpisodeId;
    private Integer episodeNumber;
    private String filmEpisodeUrl;
    private MultipartFile filmEpisodeImage;
    private Integer episodeTime;
    private Integer filmCurrentTime;
    private Long filmId;

    public FilmEpisodeDto()
    {
    }

    public FilmEpisodeDto(Integer episodeNumber, Integer episodeTime, Integer filmCurrentTime, Long filmEpisodeId, MultipartFile filmEpisodeImage, String filmEpisodeUrl, Long filmId)
    {
        this.episodeNumber = episodeNumber;
        this.episodeTime = episodeTime;
        this.filmCurrentTime = filmCurrentTime;
        this.filmEpisodeId = filmEpisodeId;
        this.filmEpisodeImage = filmEpisodeImage;
        this.filmEpisodeUrl = filmEpisodeUrl;
        this.filmId = filmId;
    }

    public Integer getEpisodeNumber()
    {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber)
    {
        this.episodeNumber = episodeNumber;
    }

    public MultipartFile getFilmEpisodeImage()
    {
        return filmEpisodeImage;
    }

    public void setFilmEpisodeImage(MultipartFile filmEpisodeImage)
    {
        this.filmEpisodeImage = filmEpisodeImage;
    }

    public Integer getEpisodeTime()
    {
        return episodeTime;
    }

    public Long getFilmId()
    {
        return filmId;
    }

    public void setFilmId(Long filmId)
    {
        this.filmId = filmId;
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
