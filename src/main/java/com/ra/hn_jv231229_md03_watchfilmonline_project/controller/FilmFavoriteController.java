package com.ra.hn_jv231229_md03_watchfilmonline_project.controller;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.response.FilmDetailResponseDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Banner;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Film;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.User;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.IFilmFavoriteService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.IFilmService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.implementation.BannerService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.implementation.FilmManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/favorites")
public class FilmFavoriteController
{

    private IFilmFavoriteService filmFavoriteService;
    private FilmManagerService filmManagerService;
    private final IFilmService filmService;
    private final BannerService bannerService;

    @Autowired
    public FilmFavoriteController(IFilmFavoriteService filmFavoriteService, FilmManagerService filmManagerService, IFilmService filmService, BannerService bannerService)
    {
        this.filmFavoriteService = filmFavoriteService;
        this.filmManagerService = filmManagerService;
        this.filmService = filmService;
        this.bannerService = bannerService;
    }

    @GetMapping("/list")
    public String addFilmToFavorites(Model model, HttpSession session)
    {
        User user = (User) session.getAttribute("user");
        if (user == null)
        {
            return "redirect:/login";
        }
        List<Film> favoriteFilms = filmFavoriteService.getFavoritesFilm(user);
        model.addAttribute("favoriteFilms", favoriteFilms);
        model.addAttribute("username", user.getUsername());
        List<Film> seriesFilm = filmService.getTopRate(true);
        List<Film> singleFilm = filmService.getTopRate(false);
        List<Film> allFilms = filmService.findAll();
        List<Banner> banners = bannerService.findAll();

        model.addAttribute("banners", banners);

        List<FilmDetailResponseDto> responseFilmList = new ArrayList<>();
        List<FilmDetailResponseDto> responseSeriesList = new ArrayList<>();
        List<FilmDetailResponseDto> responseSingleList = new ArrayList<>();
        //ALl films
        for (Film film : allFilms)
        {
            responseFilmList.add(filmService.getResponseFilm(film));
        }
        //Series list
        for (Film film : seriesFilm)
        {
            responseSeriesList.add(filmService.getResponseFilm(film));
        }
        //Single list
        for (Film film : singleFilm)
        {
            responseSingleList.add(filmService.getResponseFilm(film));
        }
        model.addAttribute("seriesFilm", responseSeriesList);
        model.addAttribute("singleFilm", responseSingleList);
        model.addAttribute("filmList", responseFilmList);
//        Film film = filmManagerService.getFilmById(filmId);
//        filmFavoriteService.addFilmToFavorites(user, film);
        return "favoriteFilm/favorite_film";
    }
//    @PostMapping("/add")
//    public String addFilmToFavorites(@RequestParam("filmId") Long filmId, HttpSession session) {
//        User user = (User) session.getAttribute("loggedInUser");
//        if (user == null) {
//            return "redirect:/login";
//        }
//        Film film = filmManagerService.getFilmById(filmId);
//        filmFavoriteService.addFilmToFavorites(user, film);
//        return "redirect:/favorites/list";
//    }

    @GetMapping("/remove/{filmId}")
    public String removeFilmFromFavorites(@PathVariable("filmId") Long filmId, HttpSession session)
    {
        User user = (User) session.getAttribute("user");
        if (user == null)
        {
            return "redirect:/login";
        }
        Film film = filmManagerService.getFilmById(filmId);
        filmFavoriteService.removeFilmFromFavorites(user, film);
        return "redirect:/favorites/list";
    }


    @GetMapping("/add/{filmId}")
    public String getFavoriteFilms(@PathVariable("filmId") Long filmId, HttpSession session)
    {
        User user = (User) session.getAttribute("user");
        if (user == null)
        {
            return "redirect:/login";
        }
        Film film = filmManagerService.getFilmById(filmId);
        filmFavoriteService.addFilmToFavorites(user, film);
        return "redirect:/favorites/list";
    }
}
