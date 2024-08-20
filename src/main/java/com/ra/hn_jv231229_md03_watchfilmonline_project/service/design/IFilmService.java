package com.ra.hn_jv231229_md03_watchfilmonline_project.service.design;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request.FilmRequestDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.response.FilmDetailResponseDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Film;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.FilmCategory;

import java.util.List;

public interface IFilmService
{
    Long saveFilm(FilmRequestDto filmRequestDto);

    List<Film> getAllFilms(int currentPage, int size);

    Film getFilmById(Long id);

    List<Film> getFilmByStatus(int status);

    List<Film> searchFilmRelative(String columnName, String infoToSearch, Long cateId, Long countryId, Boolean isFree, Integer status);

    Boolean deleteFilmById(long id);

    Integer countNumberOfFilms();

    List<Film> getTopRate(Boolean seriesSingle);

    List<Film> sortFilmList(int currentPage, int size, String columnName, Boolean isAscending);

    List<Film> findAll();

    Long countView();

    FilmDetailResponseDto getResponseFilm(Film film);

    List<Film> getRecommendFilm();
}
