package com.ra.hn_jv231229_md03_watchfilmonline_project.service.design;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request.FilmEpisodeDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.FilmEpisode;

import java.util.List;

public interface IFilmEpisodeService
{
    Boolean saveEpisode(FilmEpisodeDto filmEpisodeDto);

    List<FilmEpisode> getEpisodeListByFilmId(Long filmId);

    Boolean deleteEpisode(int id);

    void deletePreviousEpisode(long filmId);

    FilmEpisode getEpisodeById(Long episodeId);
}
