package com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "banner")
public class Banner
{
    @Id
    @Column(name = "banner_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bannerId;
    @Column(name = "banner_image")
    private String bannerImage;
    @OneToOne
    @JoinColumn(name = "film_id", referencedColumnName = "film_id")
    private Film film;

    public Banner()
    {
    }

    public Banner(Long bannerId, String bannerImage, Film film)
    {
        this.bannerId = bannerId;
        this.bannerImage = bannerImage;
        this.film = film;
    }

    public Long getBannerId()
    {
        return bannerId;
    }

    public void setBannerId(Long bannerId)
    {
        this.bannerId = bannerId;
    }

    public String getBannerImage()
    {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage)
    {
        this.bannerImage = bannerImage;
    }

    public Film getFilm()
    {
        return film;
    }

    public void setFilm(Film film)
    {
        this.film = film;
    }
}
