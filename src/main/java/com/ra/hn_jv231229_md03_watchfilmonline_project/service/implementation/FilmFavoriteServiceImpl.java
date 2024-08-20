package com.ra.hn_jv231229_md03_watchfilmonline_project.service.implementation;

import com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design.IFavoriteFilmDao;
import com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design.IUserDao;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Film;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.User;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.IFilmFavoriteService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmFavoriteServiceImpl implements IFilmFavoriteService {
    @Autowired
    private IFavoriteFilmDao favoriteFilmDao;
    @Autowired
    private IUserDao userDao;
    @Override
    public void addFilmToFavorites(User user, Film film) {
        user.getFilmSet().add(film);
        userDao.update(user);
    }

    @Override
    public void removeFilmFromFavorites(User user, Film film) {
        favoriteFilmDao.removeFilmFromFavorites(user, film);
    }

    @Override
    public List<Film> getFavoritesFilm(User user) {
        return favoriteFilmDao.getFavoritesFilm(user);
    }

}
