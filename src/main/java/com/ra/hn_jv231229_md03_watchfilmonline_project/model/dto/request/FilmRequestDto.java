package com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.FilmEpisode;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class FilmRequestDto
{
    private Long filmId;
    @NotEmpty(message = "Tên phim không được để trống")
    private String filmName;
    private String filmDescription;
    private MultipartFile fileImage;
    private String trailerUrl;
    @NotNull(message = "Vui lòng chọn quốc gia")
    private Long countryId;
    @NotNull(message = "Vui lòng chọn ngày phim ra mắt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;
    private String director;
    private String mainActorName;
    private String mainActressName;
    private String language;
    private Boolean seriesSingle;
    @Min(value = 1, message = "Số tập phim ít nhất là 1")
    private Integer totalEpisode;
    private Boolean isFree;
    @Range(min = 1, max = 3)
    private Integer status; //Status 1 = Đang chiếu, 2 = Sắp chiếu, 3 = Ngừng chiếu
    @NotNull(message = "Vui lòng chọn thể loại")
    private Long categoryId;
    private List<FilmEpisode> episodeList;

    public FilmRequestDto()
    {
    }

    public FilmRequestDto(Long categoryId, Long countryId, String director, List<FilmEpisode> episodeList, MultipartFile fileImage, String filmDescription, Long filmId, String filmName, Boolean isFree, String language, String mainActorName, String mainActressName, Date releaseDate, Boolean seriesSingle, Integer status, Integer totalEpisode, String trailerUrl)
    {
        this.categoryId = categoryId;
        this.countryId = countryId;
        this.director = director;
        this.episodeList = episodeList;
        this.fileImage = fileImage;
        this.filmDescription = filmDescription;
        this.filmId = filmId;
        this.filmName = filmName;
        this.isFree = isFree;
        this.language = language;
        this.mainActorName = mainActorName;
        this.mainActressName = mainActressName;
        this.releaseDate = releaseDate;
        this.seriesSingle = seriesSingle;
        this.status = status;
        this.totalEpisode = totalEpisode;
        this.trailerUrl = trailerUrl;
    }

    public String getDirector()
    {
        return director;
    }

    public void setDirector(String director)
    {
        this.director = director;
    }

    public MultipartFile getFileImage()
    {
        return fileImage;
    }

    public void setFileImage(MultipartFile fileImage)
    {
        this.fileImage = fileImage;
    }

    public @NotNull(message = "Vui lòng chọn ngày phim ra mắt") Date getReleaseDate()
    {
        return releaseDate;
    }

    public void setReleaseDate(@NotNull(message = "Vui lòng chọn ngày phim ra mắt") Date releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public Long getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }

    public Long getCountryId()
    {
        return countryId;
    }

    public void setCountryId(Long countryId)
    {
        this.countryId = countryId;
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

//    public Date getReleaseDate()
//    {
//        return releaseDate;
//    }
//
//    public void setReleaseDate(Date releaseDate)
//    {
//        this.releaseDate = releaseDate;
//    }

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
