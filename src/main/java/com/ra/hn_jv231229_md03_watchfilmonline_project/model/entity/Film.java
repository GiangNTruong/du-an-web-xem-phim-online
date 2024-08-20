package com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "film")
public class Film
{
    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long filmId;
    @Column(name = "film_name", unique = true)
    private String filmName;
    @Column(name = "film_description")
    private String filmDescription;
    @Column(name = "film_image")
    private String filmImage;
    @Column(name = "trailer_url")
    private String trailerUrl;
    @Column(name = "view_count")
    private Long viewCount = 0l;
    @Column(name = "release_date")
    private Date releaseDate;
    @Column(name = "director")
    private String director;
    @Column(name = "main_actor_name")
    private String mainActorName;
    @Column(name = "main_actress_name")
    private String mainActressName;
    @Column(name = "language")
    private String language;
    @Column(name = "series_single")
    private Boolean seriesSingle;
    @Column(name = "total_episode")
    private Integer totalEpisode;
    @Column(name = "is_free")
    private Boolean isFree;
    @Column(name = "status")
    @Range(min = 1, max = 3)
    private Integer status; //Status 1 = Đang chiếu, 2 = Sắp chiếu, 3 = Ngừng chiếu

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private FilmCategory filmCategory;
    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    private Country country;

    @OneToMany(mappedBy = "film", fetch = FetchType.EAGER)
    private List<FilmEpisode> episodeList;

    public Film()
    {
    }

    public Film(Long filmId, String filmName, String filmDescription, String filmImage, Long viewCount, Date releaseDate, String director, String mainActorName, String mainActressName, String language, Boolean seriesSingle, Integer totalEpisode, Boolean isFree, Integer status, FilmCategory filmCategory, Country country, List<FilmEpisode> episodeList)
    {
        this.filmId = filmId;
        this.filmName = filmName;
        this.filmDescription = filmDescription;
        this.filmImage = filmImage;
        this.viewCount = viewCount;
        this.releaseDate = releaseDate;
        this.director = director;
        this.mainActorName = mainActorName;
        this.mainActressName = mainActressName;
        this.language = language;
        this.seriesSingle = seriesSingle;
        this.totalEpisode = totalEpisode;
        this.isFree = isFree;
        this.status = status;
        this.filmCategory = filmCategory;
        this.country = country;
        this.episodeList = episodeList;
    }

    public Long getViewCount()
    {
        return viewCount;
    }

    public void setViewCount(Long viewCount)
    {
        this.viewCount = viewCount;
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

    public Long getFilmId()
    {
        return filmId;
    }

    public void setFilmId(Long filmId)
    {
        this.filmId = filmId;
    }

    public String getFilmImage()
    {
        return filmImage;
    }

    public void setFilmImage(String filmImage)
    {
        this.filmImage = filmImage;
    }

    public @NotNull String getFilmName()
    {
        return filmName;
    }

    public void setFilmName(@NotNull String filmName)
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

    public @NotNull(message = "Vui lòng chọn ngày phim ra mắt") Date getReleaseDate()
    {
        return releaseDate;
    }

    public void setReleaseDate(@NotNull(message = "Vui lòng chọn ngày phim ra mắt") Date releaseDate)
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

    public @Range(min = 1, max = 3) Integer getStatus()
    {
        return status;
    }

    public void setStatus(@Range(min = 1, max = 3) Integer status)
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

    public List<FilmEpisode> getEpisodeList()
    {
        return episodeList;
    }

    public void setEpisodeList(List<FilmEpisode> episodeList)
    {
        this.episodeList = episodeList;
    }

    public String getTrailerUrl()
    {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl)
    {
        this.trailerUrl = trailerUrl;
    }
}
