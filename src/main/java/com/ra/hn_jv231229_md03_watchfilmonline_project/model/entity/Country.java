package com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "country")
public class Country
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long countryId;
    @Column(name = "country_name")
    @NotEmpty(message = "Tên quốc gia không được để trống")
    private String countryName;
    @OneToMany(mappedBy = "country",fetch = FetchType.EAGER)
    private List<Film> filmList;

    public Country()
    {
    }

    public Country(Long countryId, String countryName, List<Film> filmList)
    {
        this.countryId = countryId;
        this.countryName = countryName;
        this.filmList = filmList;
    }

    public Long getCountryId()
    {
        return countryId;
    }

    public void setCountryId(Long countryId)
    {
        this.countryId = countryId;
    }

    public @NotNull(message = "Tên quốc gia không được để trống") String getCountryName()
    {
        return countryName;
    }

    public void setCountryName(@NotNull(message = "Tên quốc gia không được để trống") String countryName)
    {
        this.countryName = countryName;
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
