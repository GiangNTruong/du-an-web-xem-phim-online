package com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Film;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.User;

import java.util.List;

public interface IFavoriteFilmDao {
//    void addFIlmToFavorites(User user, Film film);

    //    void addFIlmToFavorites(User user, Film film);
    void removeFilmFromFavorites(User user,Film film);
    List<Film> getFavoritesFilm(User user);
}
