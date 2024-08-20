package com.ra.hn_jv231229_md03_watchfilmonline_project.service.implementation;

import com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design.IFilmEpisodeDao;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request.FilmEpisodeDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.FilmEpisode;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.IFilmEpisodeService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.IFilmService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.util.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmEpisodeService implements IFilmEpisodeService
{
    private final IFilmEpisodeDao filmEpisodeDao;
    private final IFilmService filmService;
    private final FileUploadService fileUploadService;

    @Autowired
    public FilmEpisodeService(IFilmEpisodeDao filmEpisodeDao, IFilmService filmService, FileUploadService fileUploadService)
    {
        this.filmEpisodeDao = filmEpisodeDao;
        this.filmService = filmService;
        this.fileUploadService = fileUploadService;
    }

    @Override
    public Boolean saveEpisode(FilmEpisodeDto filmEpisodeDto)
    {
        FilmEpisode filmEpisode = new FilmEpisode();
        filmEpisode.setEpisodeNumber(filmEpisodeDto.getEpisodeNumber());
        filmEpisode.setEpisodeTime(filmEpisodeDto.getEpisodeTime());
        filmEpisode.setFilmEpisodeId(filmEpisodeDto.getFilmEpisodeId());
        filmEpisode.setFilmEpisodeUrl(filmEpisodeDto.getFilmEpisodeUrl());
        filmEpisode.setFilm(filmService.getFilmById(filmEpisodeDto.getFilmId()));
        if (filmEpisodeDto.getFilmEpisodeId() == null)
        {
            filmEpisode.setFilmEpisodeImage(fileUploadService.uploadFileToServer(filmEpisodeDto.getFilmEpisodeImage()));
        } else
        {
            if (filmEpisodeDto.getFilmEpisodeImage() != null && filmEpisodeDto.getFilmEpisodeImage().getSize() > 0)
            {
                filmEpisode.setFilmEpisodeImage(fileUploadService.uploadFileToServer(filmEpisodeDto.getFilmEpisodeImage()));
            } else
            {
                filmEpisode.setFilmEpisodeImage(filmEpisodeDao.getFilmImageById(filmEpisodeDto.getFilmEpisodeId()));
            }
        }
        return filmEpisodeDao.saveEpisode(filmEpisode);
    }

    @Override
    public List<FilmEpisode> getEpisodeListByFilmId(Long filmId)
    {
        return filmEpisodeDao.getEpisodeListByFilmId(filmId);
    }

    @Override
    public Boolean deleteEpisode(int id)
    {
        return filmEpisodeDao.deleteEpisode(id);
    }

    @Override
    public void deletePreviousEpisode(long filmId)
    {
        filmEpisodeDao.deletePreviousEpisode(filmId);
    }

    @Override
    public FilmEpisode getEpisodeById(Long episodeId)
    {
        return filmEpisodeDao.getEpisodeById(episodeId);
    }
}
