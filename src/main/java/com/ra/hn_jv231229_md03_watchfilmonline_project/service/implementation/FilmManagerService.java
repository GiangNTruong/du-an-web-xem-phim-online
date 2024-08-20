package com.ra.hn_jv231229_md03_watchfilmonline_project.service.implementation;

import com.mysql.cj.Session;
import com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design.ICategoryDao;
import com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design.ICountryDao;
import com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design.IFilmManageDao;
import com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design.*;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request.FilmRequestDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.response.FilmDetailResponseDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Comment;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Film;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.FilmCategory;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.FilmEpisode;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.IFilmService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.util.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class FilmManagerService implements IFilmService
{
    private final IFilmManageDao filmManageDao;
    private final FileUploadService fileUploadService;
    private final ICategoryDao categoryDao;
    private final ICountryDao countryDao;
    private final ICommentDao commentDao;
    private final IFilmEpisodeDao episodeDao;

    @Autowired
    public FilmManagerService(IFilmManageDao filmManageDao, FileUploadService fileUploadService, ICategoryDao categoryDao, ICountryDao countryDao, ICommentDao commentDao, IFilmEpisodeDao episodeDao)
    {
        this.filmManageDao = filmManageDao;
        this.fileUploadService = fileUploadService;
        this.categoryDao = categoryDao;
        this.countryDao = countryDao;
        this.commentDao = commentDao;
        this.episodeDao = episodeDao;
    }

    @Override
    public Long saveFilm(FilmRequestDto filmRequestDto)
    {
        Film film = new Film();
        film.setFilmId(filmRequestDto.getFilmId());
        film.setDirector(filmRequestDto.getDirector());
        film.setFilmDescription(filmRequestDto.getFilmDescription());
        film.setTrailerUrl(filmRequestDto.getTrailerUrl());
        film.setFilmName(filmRequestDto.getFilmName());
        film.setFree(filmRequestDto.getFree());
        film.setLanguage(filmRequestDto.getLanguage());
        film.setMainActorName(filmRequestDto.getMainActorName());
        film.setMainActressName(filmRequestDto.getMainActressName());
        film.setReleaseDate(filmRequestDto.getReleaseDate());
        film.setSeriesSingle(filmRequestDto.getSeriesSingle());
        film.setStatus(filmRequestDto.getStatus());
        film.setTotalEpisode(filmRequestDto.getTotalEpisode());
        film.setFilmCategory(categoryDao.findById(filmRequestDto.getCategoryId()));
        film.setCountry(countryDao.findById(filmRequestDto.getCountryId()));
        if (filmRequestDto.getFilmId() == null)
        {
            film.setFilmImage(fileUploadService.uploadFileToServer(filmRequestDto.getFileImage()));
        } else
        {
            if (filmRequestDto.getFileImage() != null && filmRequestDto.getFileImage().getSize() > 0)
            {
                film.setFilmImage(fileUploadService.uploadFileToServer(filmRequestDto.getFileImage()));
            } else
            {
                film.setFilmImage(filmManageDao.getFilmImageById(filmRequestDto.getFilmId()));
            }
        }
        return filmManageDao.saveFilm(film);
    }

    @Override
    public List<Film> getAllFilms(int currentPage, int size)
    {
        return filmManageDao.getAllFilms(currentPage, size);
    }

    @Override
    public Film getFilmById(Long id)
    {
        return filmManageDao.getFilmById(id);
    }

    @Override
    public List<Film> getFilmByStatus(int status)
    {
        return filmManageDao.getFilmByStatus(status);
    }

    @Override
    public List<Film> searchFilmRelative(String columnName, String infoToSearch, Long cateId, Long countryId, Boolean isFree, Integer status)
    {
        String info = null;
        if (infoToSearch == null)
        {
            infoToSearch = "";
        }
        info = "%" + infoToSearch + "%";
        return filmManageDao.searchFilmRelative(columnName, info, cateId, countryId, isFree, status);
    }

    @Override
    public Boolean deleteFilmById(long id)
    {
        return filmManageDao.deleteFilmById(id);
    }

    @Override
    public Integer countNumberOfFilms()
    {
        return filmManageDao.countNumberOfFilms();
    }


    @Override
    public List<Film> getTopRate(Boolean seriesSingle)
    {
        List<Film> list = filmManageDao.getTopRate(seriesSingle).stream().sorted(Comparator.comparingLong(Film::getViewCount).reversed()).limit(4).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<Film> sortFilmList(int currentPage, int size, String columnName, Boolean isAscending)
    {
        //Lấy ra toàn bộ list để sort trước để khi đổi page không bị loạn thứ tự
        List<Film> filmList = findAll();
        switch (columnName)
        {
            case "filmName":
                if (isAscending)
                {//Sắp xếp tăng dần, lấy ra sublist với start và end index
                    return filmList.stream().sorted(Comparator.comparing(Film::getFilmName)).collect(Collectors.toList()).
                            subList(currentPage, Math.min((currentPage + size), filmList.size()));
                }
                //Sắp xếp giảm dần, lấy ra sublist với start và end index
                return filmList.stream().sorted(Comparator.comparing(Film::getFilmName).reversed()).collect(Collectors.toList())
                        .subList(currentPage, Math.min((currentPage + size), filmList.size()));
            case "filmId":
                if (isAscending)
                {
                    return filmList.stream().sorted(Comparator.comparing(Film::getFilmId)).collect(Collectors.toList())
                            .subList(currentPage, Math.min((currentPage + size), filmList.size()));
                }
                return filmList.stream().sorted(Comparator.comparing(Film::getFilmId).reversed()).collect(Collectors.toList())
                        .subList(currentPage, Math.min((currentPage + size), filmList.size()));
            case "categoryName":
                if (isAscending)
                {
                    return filmList.stream().sorted(Comparator.comparing(f -> f.getFilmCategory().getCategoryName())).collect(Collectors.toList())
                            .subList(currentPage, Math.min((currentPage + size), filmList.size()));
                }
                return filmList.stream().sorted((f1, f2) -> f2.getFilmCategory().getCategoryName().compareTo(f1.getFilmCategory().getCategoryName()))
                        .collect(Collectors.toList()).subList(currentPage, Math.min((currentPage + size), filmList.size()));
            default:
                return filmList;
        }
    }

    @Override
    public List<Film> findAll()
    {
        return filmManageDao.findAll();
    }

    @Override

    public Long countView()
    {
        return filmManageDao.countView();
    }


    public FilmDetailResponseDto getResponseFilm(Film film)
    {
        FilmDetailResponseDto filmResponse = new FilmDetailResponseDto();
        filmResponse.setFilmId(film.getFilmId());
        filmResponse.setDirector(film.getDirector());
        filmResponse.setFilmDescription(film.getFilmDescription());
        filmResponse.setFilmImage(film.getFilmImage());
        filmResponse.setFilmName(film.getFilmName());
        filmResponse.setFree(film.getFree());
        filmResponse.setLanguage(film.getLanguage());
        filmResponse.setMainActorName(film.getMainActorName());
        filmResponse.setMainActressName(film.getMainActressName());
        filmResponse.setReleaseDate(film.getReleaseDate());
        filmResponse.setSeriesSingle(film.getSeriesSingle());
        filmResponse.setStatus(film.getStatus());
        filmResponse.setTotalEpisode(film.getTotalEpisode());
        filmResponse.setTrailerUrl(film.getTrailerUrl());
        filmResponse.setFilmCategory(film.getFilmCategory());
        filmResponse.setCountry(film.getCountry());
        filmResponse.setEpisodeList(episodeDao.getEpisodeListByFilmId(film.getFilmId()));
        int sumTime = 0;
        for (FilmEpisode episode : episodeDao.getEpisodeListByFilmId(film.getFilmId()))
        {
            sumTime += episode.getEpisodeTime();
        }
        filmResponse.setTotalShowTime(sumTime);
        List<Comment> commentList = commentDao.findCommentByFilm(film.getFilmId());
        double starsAverage = commentList.stream().mapToDouble(Comment::getStars).average().orElse(0);
        filmResponse.setStars(starsAverage);
        return filmResponse;
    }

    @Override
    public List<Film> getRecommendFilm()
    {
        List<Film> filmList = filmManageDao.getRecommendFilm();
        Collections.shuffle(filmList);
        return filmList.subList(0, Math.min(4, filmList.size()));
    }

}
