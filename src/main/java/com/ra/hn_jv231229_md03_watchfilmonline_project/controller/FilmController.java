package com.ra.hn_jv231229_md03_watchfilmonline_project.controller;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request.EpisodeListDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request.FilmAdvanceSearchDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request.FilmEpisodeDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request.FilmRequestDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Film;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.FilmEpisode;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.ICategoryService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.ICountryService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.IFilmEpisodeService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.IFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/film")
public class FilmController
{
    private final IFilmService filmService;
    private final IFilmEpisodeService episodeService;
    private final ICategoryService categoryService;
    private final ICountryService countryService;

    @Autowired
    public FilmController(IFilmService filmService, IFilmEpisodeService episodeService, ICategoryService categoryService, ICountryService countryService)
    {
        this.filmService = filmService;
        this.episodeService = episodeService;
        this.categoryService = categoryService;
        this.countryService = countryService;
    }

    @GetMapping("/list")
    public String listFilm(Model model, @RequestParam(defaultValue = "1") int currentPage
            , @RequestParam(value = "columnName", defaultValue = "filmId") String columnName
            , @RequestParam(value = "isAscending", defaultValue = "true") Boolean isAscending)
    {
        int size = 5;
        //Index trong hql bắt đầu từ 0 => Lấy currentPage -1. Nhân với size để ra index bắt đầu
        model.addAttribute("listFilm", filmService.sortFilmList((currentPage - 1) * size, size,
                columnName, isAscending));
//        model.addAttribute("listFilm", filmService.sortFilmList(0, 1000,
//                columnName, isAscending));
        //Tổng số phim hiện có / size => Số trang cần hiển thị. Ép kiểu về Int vì math.ceil trả về double
        model.addAttribute("totalPages", (int) Math.ceil((double) filmService.countNumberOfFilms() / size));
        model.addAttribute("columnName", columnName);
        model.addAttribute("isAscending", isAscending);
        model.addAttribute("currentPage", currentPage);
        return "film/list-film";
    }

    @GetMapping("/initAdd")
    public String initAdd(Model model)
    {
        model.addAttribute("filmModel", new FilmRequestDto());
        model.addAttribute("categoryList", categoryService.findAll(0, 1000));
        model.addAttribute("countryList", countryService.findAll());
        return "film/add-film-view";
    }

    @GetMapping("/initEdit/{id}")
    public String initEdit(@PathVariable("id") long filmId, Model model)
    {
        Film film = filmService.getFilmById(filmId);
        model.addAttribute("categoryList", categoryService.findAll(0, 1000));
        model.addAttribute("filmModel", setAttributeDto(filmId, film));
        model.addAttribute("countryList", countryService.findAll());
        model.addAttribute("categoryList", categoryService.findAll(0, 1000));
        model.addAttribute("filmModel", setAttributeDto(filmId, film));
        return "film/edit-film-view";
    }

    @PostMapping("/addFilm")
    public String addFilm(@Valid @ModelAttribute("filmModel") FilmRequestDto filmRequestDto, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            model.addAttribute("filmModel", filmRequestDto);
            model.addAttribute("countryList", countryService.findAll());
            model.addAttribute("categoryList", categoryService.findAll(0, 1000));
            return "film/add-film-view";
        }
        EpisodeListDto episodeListDto = new EpisodeListDto();
        //Nếu là phim lẻ thì set số tập về 1. Không thì lấy số tập đã nhập ở trang trước đó
        if (filmRequestDto.getTotalEpisode() > 1)
        {   //Số tập lớn hơn 1 => series
            filmRequestDto.setSeriesSingle(true);
        } else
        {   //Số tập bằng 1 => Single
            filmRequestDto.setSeriesSingle(false);
        }
//        List<FilmEpisode> episodeList = new ArrayList<>();
        List<FilmEpisodeDto> episodeDtoList = new ArrayList<>();
        //Wrapper class
//        filmRequestDto.setEpisodeList(episodeList);
        episodeListDto.setEpisodeDtoList(episodeDtoList);
        //Vừa save vừa lấy Id của film này trong database
        filmRequestDto.setFilmId(filmService.saveFilm(filmRequestDto));
        for (int i = 0; i < filmRequestDto.getTotalEpisode(); i++)
        {
//            episodeList.add(new FilmEpisode());
            FilmEpisodeDto episodeDto = new FilmEpisodeDto();
            episodeDto.setFilmId(filmRequestDto.getFilmId());
            episodeDto.setEpisodeNumber(i + 1);
            episodeDtoList.add(episodeDto);
        }
        model.addAttribute("episodeListDto", episodeListDto);
//        model.addAttribute("newFilm", filmRequestDto);
        return "film/add-film-episode";
    }

    @PostMapping("/editFilm")
    public String editFilm(@Valid @ModelAttribute("filmModel") FilmRequestDto filmRequestDto, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            model.addAttribute("filmModel", filmRequestDto);
            model.addAttribute("categoryList", categoryService.findAll(0, 1000));
            model.addAttribute("countryList", countryService.findAll());
            return "film/edit-film-view";
        }
        //Tạo ra object giữ list các episodeDto
        EpisodeListDto episodeListDto = new EpisodeListDto();
        //Nếu là phim lẻ thì set số tập về 1. Không thì lấy số tập đã nhập ở trang trước đó
        if (filmRequestDto.getTotalEpisode() > 1)
        {   //Số tập lớn hơn 1 => series
            filmRequestDto.setSeriesSingle(true);
        } else
        {   //Số tập bằng 1 => Single
            filmRequestDto.setSeriesSingle(false);
        }
//        List<FilmEpisode> episodeList = new ArrayList<>();
        //Tạo ra list để gán vào object episodeListDto
        List<FilmEpisodeDto> episodeDtoList = new ArrayList<>();
        //Wrapper class
//        filmRequestDto.setEpisodeList(episodeList);
        //Gán list vào trong object
        episodeListDto.setEpisodeDtoList(episodeDtoList);
        //Vừa save vừa lấy Id của film này trong database
        filmRequestDto.setFilmId(filmService.saveFilm(filmRequestDto));
        if (filmRequestDto.getTotalEpisode() != episodeService.getEpisodeListByFilmId(filmRequestDto.getFilmId()).size())
        { //Nếu số tập có sự thay đổi => Tạo ra các episode mới
            for (int i = 0; i < filmRequestDto.getTotalEpisode(); i++)
            {
//            episodeList.add(new FilmEpisode());
                FilmEpisodeDto episodeDto = new FilmEpisodeDto();
                episodeDto.setFilmId(filmRequestDto.getFilmId());
                episodeDto.setEpisodeNumber(i + 1);
                episodeDtoList.add(episodeDto);
            }
            //Trước khi thêm các tập mới thì xóa đi các tập phim cũ
            episodeService.deletePreviousEpisode(filmRequestDto.getFilmId());
        } else
        {
            //Nếu không thì hiển thị lại thông tin các tập cũ
            for (FilmEpisode fe : episodeService.getEpisodeListByFilmId(filmRequestDto.getFilmId()))
            {
                FilmEpisodeDto episodeDto = new FilmEpisodeDto();
                episodeDto.setFilmId(filmRequestDto.getFilmId());
                episodeDto.setEpisodeNumber(fe.getEpisodeNumber());
                episodeDto.setEpisodeTime(fe.getEpisodeTime());
                episodeDto.setFilmEpisodeUrl(fe.getFilmEpisodeUrl());
                episodeDto.setFilmEpisodeId(fe.getFilmEpisodeId());
                episodeDtoList.add(episodeDto);
            }
        }
        model.addAttribute("episodeListDto", episodeListDto);
//        model.addAttribute("newFilm", filmRequestDto);
        return "film/edit-film-episode";
    }

    @GetMapping("/search")
    public String searchFilm()
    {
        return "film/search-film-view";
    }

    @PostMapping("/addEpisode")
    public String addEpisode(@ModelAttribute EpisodeListDto episodeListDto)
    {
        for (FilmEpisodeDto episode : episodeListDto.getEpisodeDtoList())
        {
            episodeService.saveEpisode(episode);
        }
        return "redirect:/film/list";
    }

    @PostMapping("/editEpisode")
    public String editEpisode(@ModelAttribute EpisodeListDto episodeListDto)
    {
        for (FilmEpisodeDto episode : episodeListDto.getEpisodeDtoList())
        {
            episodeService.saveEpisode(episode);
        }
        return "redirect:/film/list";
    }

    @GetMapping("/deleteFilm/{id}")
    public String deleteFilm(@PathVariable("id") long filmId)
    {
        filmService.deleteFilmById(filmId);
        return "redirect:/film/list";
    }

    @GetMapping("/searchFilmName")
    public String listFilmByName(@RequestParam("infoToSearch") String infoToSearch, Model model)
    {
//        List<Film> filmsFound = filmService.searchFilmRelative(infoToSearch, "filmName");
        Long cateId = null;
        Long countryId = null;
        Boolean seriresSingle = null;
        Integer status = null;
        List<Film> filmsFound = filmService.searchFilmRelative("filmName", infoToSearch, cateId, countryId, seriresSingle, status);
        model.addAttribute("filmsFound", filmsFound);
        return "film/search-film-view";
    }

    @GetMapping("/advanceSearch")
    public String advanceSearch(Model model)
    {
        model.addAttribute("filmSearch", new FilmAdvanceSearchDto());
        model.addAttribute("categoryList", categoryService.findAll(0, 1000));
        return "film/advance-search";
    }

    @PostMapping("/doAdvanceSearch")
    public String doAdvanceSearch(@ModelAttribute("filmSearch") FilmAdvanceSearchDto filmSearch, @RequestParam("columnName") String columnName, @RequestParam("infoToSearch") String infoToSearch, Model model)
    {
        Long cateId = filmSearch.getCategoryId();
        Long countryId = filmSearch.getCountryId();

        Boolean isFree = filmSearch.getFree();
        Integer status = filmSearch.getStatus();

        List<Film> filmsFound = filmService.searchFilmRelative(columnName, infoToSearch, cateId, countryId, isFree, status);
//        model.addAttribute("filmSearch", new Film());
        model.addAttribute("categoryList", categoryService.findAll(0, 1000));
        model.addAttribute("filmsFound", filmsFound);
        return "film/search-film-view";
    }

    private FilmRequestDto setAttributeDto(Long filmId, Film film)
    {
        FilmRequestDto filmRequestDto = new FilmRequestDto();
        filmRequestDto.setFilmId(filmId);
        filmRequestDto.setDirector(film.getDirector());
        filmRequestDto.setFilmDescription(film.getFilmDescription());
        filmRequestDto.setFilmName(film.getFilmName());
        filmRequestDto.setFree(film.getFree());
        filmRequestDto.setLanguage(film.getLanguage());
        filmRequestDto.setMainActorName(film.getMainActorName());
        filmRequestDto.setMainActressName(film.getMainActressName());
        filmRequestDto.setReleaseDate(film.getReleaseDate());
        filmRequestDto.setSeriesSingle(film.getSeriesSingle());
        filmRequestDto.setStatus(film.getStatus());
        filmRequestDto.setTotalEpisode(film.getTotalEpisode());
        filmRequestDto.setTrailerUrl(film.getTrailerUrl());
        filmRequestDto.setCategoryId(film.getFilmCategory().getCategoryId());
        filmRequestDto.setCountryId(film.getCountry().getCountryId());
        filmRequestDto.setEpisodeList(episodeService.getEpisodeListByFilmId(filmId));
        return filmRequestDto;
    }
}
