package com.ra.hn_jv231229_md03_watchfilmonline_project.controller;

import com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design.IFilmManageDao;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.constant.UserRole;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.*;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.mapper.UserMapper;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request.CommentDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Banner;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Film;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.IBannerService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request.FilmEpisodeDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request.FilmRequestDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.response.FilmDetailResponseDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Film;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.FilmEpisode;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.ICommentService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.IFilmEpisodeService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.IFilmService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class HomePageController
{
    private final IFilmService filmService;
    private final IFilmEpisodeService episodeService;
    private final IUserService userService;
    private final ICommentService commentService;
    private final IBannerService bannerService;
    private final HttpSession session;

    @Autowired
    public HomePageController(IFilmService filmService, IFilmEpisodeService episodeService, IUserService userService, ICommentService commentService, IBannerService bannerService, HttpSession session)
    {
        this.filmService = filmService;
        this.episodeService = episodeService;
        this.userService = userService;
        this.commentService = commentService;
        this.bannerService = bannerService;
        this.session = session;
    }

    @RequestMapping("/")
    public String index(Model model)
    {
        List<Film> recommendFilm = filmService.getRecommendFilm();
        List<Film> seriesFilm = filmService.getTopRate(true);
        List<Film> singleFilm = filmService.getTopRate(false);
        List<Film> allFilms = filmService.findAll();
        List<Banner> banners = bannerService.findAll();

        model.addAttribute("banners", banners);

        List<FilmDetailResponseDto> responseFilmList = new ArrayList<>();
        List<FilmDetailResponseDto> responseSeriesList = new ArrayList<>();
        List<FilmDetailResponseDto> responseSingleList = new ArrayList<>();
        List<FilmDetailResponseDto> responseRecommend = new ArrayList<>();
        //Recommend films
        for (Film film : recommendFilm)
        {
            responseRecommend.add(filmService.getResponseFilm(film));
        }
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
        User user = (User) session.getAttribute("user");
        if (user != null)
        {
            model.addAttribute("username", user.getUsername());
        }
        model.addAttribute("recommendFilm", responseRecommend);
        return "index";
    }

    @GetMapping("/movie-detail/{id}")
    public String movieDetail(@PathVariable Long id, Model model, HttpSession session)
    {
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("detailFilm", filmService.getResponseFilm(filmService.getFilmById(id)));
        model.addAttribute("listComment", commentService.findCommentByFilm(id));
        model.addAttribute("userId", userId);
        model.addAttribute("filmId", id);
        CommentDto commentDto = new CommentDto();
        commentDto.setFilmId(id);
        commentDto.setUserId(userId);
        model.addAttribute("commentDto", commentDto);
        return "movie-details";
    }


    @RequestMapping("/home")
    public String home()
    {
        return "index";
    }

    @GetMapping("/category_film")
    public String categoryFilm()
    {
        return "category/category_film";
    }

    @GetMapping("/play-episode/{idEpisode}")
    public String playEpisode(@PathVariable("idEpisode") Long idEpisode
            , @RequestParam("filmId") Long filmId
            , Model model)
    {
        model.addAttribute("idEpisode", idEpisode);
        FilmEpisodeDto filmEpisodeDto = new FilmEpisodeDto();
        FilmEpisode episodeToPlay = episodeService.getEpisodeById(idEpisode);
        filmEpisodeDto.setFilmId(episodeToPlay.getFilmEpisodeId());
        filmEpisodeDto.setEpisodeTime(episodeToPlay.getEpisodeTime());
        filmEpisodeDto.setEpisodeNumber(episodeToPlay.getEpisodeNumber());
        filmEpisodeDto.setFilmEpisodeUrl(episodeToPlay.getFilmEpisodeUrl());
        filmEpisodeDto.setFilmCurrentTime(episodeToPlay.getFilmCurrentTime());
        filmEpisodeDto.setFilmId(idEpisode);
        model.addAttribute("episodeToPlay", filmEpisodeDto);
        model.addAttribute("detailFilm", filmService.getResponseFilm(filmService.getFilmById(filmId)));
        userService.addHistory(idEpisode, (User) session.getAttribute("user"));
        return "movie-details";
    }

    @GetMapping("/play-trailer/{idFilm}")
    public String playTrailer(@PathVariable("idFilm") Long idFilm, Model model)
    {
        model.addAttribute("filmWithTrailer", filmService.getFilmById(idFilm));
        model.addAttribute("detailFilm", filmService.getResponseFilm(filmService.getFilmById(idFilm)));
        return "movie-details";
    }

    @RequestMapping("/check-role/{userId}")
    public String checkRole(@PathVariable("userId") Long userId, @RequestParam("idEpisode") Long idEpisode
            , @RequestParam("filmId") Long filmId
            , Model model)
    {
        model.addAttribute("detailFilm", filmService.getResponseFilm(filmService.getFilmById(filmId)));
        if (userId == -1)
        {
            model.addAttribute("roleFree", "Vui lòng đăng nhập để xem phim");
            return "movie-details";
        }
        User user = userService.findById(userId);
        //Nếu là phim không free và user không phải paid thì không cho xem
        if (user.getUserRole() == UserRole.FREE && !filmService.getFilmById(filmId).getFree())
        {
            model.addAttribute("roleFree", "Vui lòng đăng ký gói trả phí để xem phim này");
            return "movie-details";
        }
//        model.addAttribute("idEpisode", idEpisode);
        //Tạo ra set các episode để lưu vào trong lịch sử xem của người dùng
//        Set<FilmEpisode> filmEpisodeSet = new HashSet<>();
//        filmEpisodeSet.add(episodeService.getEpisodeById(idEpisode));
//        user.setFilmEpisodeSet(filmEpisodeSet);
//        try
//        {
//            user.setAvatar("C:\\Users\\Black Pig\\Pictures\\BnS\\348283600_632267798487082_462398122084732790_n.png");
//            userService.update(UserMapper.toUserDTO(user));
//        } catch (ParseException e)
//        {
//            throw new RuntimeException(e);
//        }
        return "redirect:/play-episode/" + idEpisode + "?filmId=" + filmId;
    }
}
