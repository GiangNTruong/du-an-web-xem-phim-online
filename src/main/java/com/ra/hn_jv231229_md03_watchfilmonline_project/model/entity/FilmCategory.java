package com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "film_category")
public class FilmCategory
{
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    @Column(name = "category_name")
    @NotEmpty(message = "Tên thể loại không được để trống")
    private String categoryName;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private Boolean status = true;
    @OneToMany(mappedBy = "filmCategory")
    private List<Film> filmList;

    public FilmCategory()
    {
    }

    public FilmCategory(Long categoryId, String categoryName, String description, List<Film> filmList, Boolean status)
    {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.filmList = filmList;
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

    public @NotNull String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(@NotNull String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Boolean getStatus()
    {
        return status;
    }

    public void setStatus(Boolean status)
    {
        this.status = status;
    }

    public List<Film> getFilmList()
    {
        return filmList;
    }

    public void setFilmList(List<Film> filmList)
    {
        this.filmList = filmList;
    }
}
