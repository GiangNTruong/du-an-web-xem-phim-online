package com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.response;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Country;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.FilmCategory;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.FilmEpisode;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class FilmDetailResponseDto
{
    private Long filmId;
    private String filmName;
    private String filmDescription;
    private String filmImage;
    private String trailerUrl;
    private Long viewCount = 0l;
    private Date releaseDate;
    private String director;
    private String mainActorName;
    private String mainActressName;
    private String language;
    private Boolean seriesSingle;
    private Integer totalEpisode;
    private Boolean isFree;
    private Integer status; //Status 1 = Đang chiếu, 2 = Sắp chiếu, 3 = Ngừng chiếu
    private FilmCategory filmCategory;
    private Country country;
    private List<FilmEpisode> episodeList;
    private Integer totalShowTime;
    private Double stars;

    public FilmDetailResponseDto()
    {
    }

    public FilmDetailResponseDto(Country country, String director, List<FilmEpisode> episodeList, FilmCategory filmCategory, String filmDescription, Long filmId, String filmImage, String filmName, Boolean isFree, String language, String mainActorName, String mainActressName, Date releaseDate, Boolean seriesSingle, Double stars, Integer status, Integer totalEpisode, Integer totalShowTime, String trailerUrl, Long viewCount)
    {
        this.country = country;
        this.director = director;
        this.episodeList = episodeList;
        this.filmCategory = filmCategory;
        this.filmDescription = filmDescription;
        this.filmId = filmId;
        this.filmImage = filmImage;
        this.filmName = filmName;
        this.isFree = isFree;
        this.language = language;
        this.mainActorName = mainActorName;
        this.mainActressName = mainActressName;
        this.releaseDate = releaseDate;
        this.seriesSingle = seriesSingle;
        this.stars = stars;
        this.status = status;
        this.totalEpisode = totalEpisode;
        this.totalShowTime = totalShowTime;
        this.trailerUrl = trailerUrl;
        this.viewCount = viewCount;
    }

    public Long getFilmId()
    {
        return filmId;
    }

    public void setFilmId(Long filmId)
    {
        this.filmId = filmId;
    }

    public Country getCountry()
    {
        return country;
    }

    public void setCountry(Country country)
    {
        this.country = country;
    }

    public String getDirector()
    {
        return director;
    }

    public void setDirector(String director)
    {
        this.director = director;
    }

    public List<FilmEpisode> getEpisodeList()
    {
        return episodeList;
    }

    public void setEpisodeList(List<FilmEpisode> episodeList)
    {
        this.episodeList = episodeList;
    }

    public FilmCategory getFilmCategory()
    {
        return filmCategory;
    }

    public void setFilmCategory(FilmCategory filmCategory)
    {
        this.filmCategory = filmCategory;
    }

    public String getFilmDescription()
    {
        return filmDescription;
    }

    public void setFilmDescription(String filmDescription)
    {
        this.filmDescription = filmDescription;
    }

    public String getFilmImage()
    {
        return filmImage;
    }

    public void setFilmImage(String filmImage)
    {
        this.filmImage = filmImage;
    }

    public String getFilmName()
    {
        return filmName;
    }

    public void setFilmName(String filmName)
    {
        this.filmName = filmName;
    }

    public Boolean getFree()
    {
        return isFree;
    }

    public void setFree(Boolean free)
    {
        isFree = free;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getMainActorName()
    {
        return mainActorName;
    }

    public void setMainActorName(String mainActorName)
    {
        this.mainActorName = mainActorName;
    }

    public String getMainActressName()
    {
        return mainActressName;
    }

    public void setMainActressName(String mainActressName)
    {
        this.mainActressName = mainActressName;
    }

    public Date getReleaseDate()
    {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public Boolean getSeriesSingle()
    {
        return seriesSingle;
    }

    public void setSeriesSingle(Boolean seriesSingle)
    {
        this.seriesSingle = seriesSingle;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getTotalEpisode()
    {
        return totalEpisode;
    }

    public void setTotalEpisode(Integer totalEpisode)
    {
        this.totalEpisode = totalEpisode;
    }

    public String getTrailerUrl()
    {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl)
    {
        this.trailerUrl = trailerUrl;
    }

    public Long getViewCount()
    {
        return viewCount;
    }

    public void setViewCount(Long viewCount)
    {
        this.viewCount = viewCount;
    }

    public Integer getTotalShowTime()
    {
        return totalShowTime;
    }

    public void setTotalShowTime(Integer totalShowTime)
    {
        this.totalShowTime = totalShowTime;
    }

    public Double getStars()
    {
        return stars;
    }

    public void setStars(Double stars)
    {
        this.stars = stars;
    }
}
