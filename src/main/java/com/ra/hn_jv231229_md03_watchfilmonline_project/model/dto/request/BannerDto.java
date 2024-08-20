package com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Film;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

public class BannerDto {
    private Long bannerId;
    private MultipartFile bannerImageFile;
    private String bannerImageUrl;
    private Film film;

    public BannerDto() {
    }

    public BannerDto(Long bannerId, MultipartFile bannerImageFile, String bannerImageUrl, Film film) {
        this.bannerId = bannerId;
        this.bannerImageFile = bannerImageFile;
        this.bannerImageUrl = bannerImageUrl;
        this.film = film;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Long getBannerId() {
        return bannerId;
    }

    public void setBannerId(Long bannerId) {
        this.bannerId = bannerId;
    }

    public MultipartFile getBannerImageFile() {
        return bannerImageFile;
    }

    public void setBannerImageFile(MultipartFile bannerImageFile) {
        this.bannerImageFile = bannerImageFile;
    }

    public String getBannerImageUrl() {
        return bannerImageUrl;
    }

    public void setBannerImageUrl(String bannerImageUrl) {
        this.bannerImageUrl = bannerImageUrl;
    }


}
