package com.ra.hn_jv231229_md03_watchfilmonline_project.service.design;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Film;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.User;

import java.util.List;

public interface IFilmFavoriteService {
    void addFilmToFavorites(User user, Film film);
    void removeFilmFromFavorites(User user,Film film);
    List<Film> getFavoritesFilm(User user);
}
