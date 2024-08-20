package com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request;

public class FilmAdvanceSearchDto
{
    private Long filmId;
    private String filmName;
    private String filmDescription;
    private Long countryId;
    private String director;
    private String mainActorName;
    private String mainActressName;
    private String language;
    private Boolean isFree;
    private Integer status; //Status 1 = Đang chiếu, 2 = Sắp chiếu, 3 = Ngừng chiếu
    private Long categoryId;

    public FilmAdvanceSearchDto()
    {
    }

    public FilmAdvanceSearchDto(Long categoryId, Long countryId, String director, String filmDescription, Long filmId, String filmName, Boolean isFree, String language, String mainActorName, String mainActressName, Integer status)
    {
        this.categoryId = categoryId;
        this.countryId = countryId;
        this.director = director;
        this.filmDescription = filmDescription;
        this.filmId = filmId;
        this.filmName = filmName;
        this.isFree = isFree;
        this.language = language;
        this.mainActorName = mainActorName;
        this.mainActressName = mainActressName;
        this.status = status;
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

    public String getDirector()
    {
        return director;
    }

    public void setDirector(String director)
    {
        this.director = director;
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

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }
}
