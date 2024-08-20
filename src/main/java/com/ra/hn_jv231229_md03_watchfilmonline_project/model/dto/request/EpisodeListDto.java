package com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request;

import java.util.List;

public class EpisodeListDto
{
    private List<FilmEpisodeDto> episodeDtoList;

    public EpisodeListDto()
    {
    }

    public EpisodeListDto(List<FilmEpisodeDto> episodeDtoList)
    {
        this.episodeDtoList = episodeDtoList;
    }

    public List<FilmEpisodeDto> getEpisodeDtoList()
    {
        return episodeDtoList;
    }

    public void setEpisodeDtoList(List<FilmEpisodeDto> episodeDtoList)
    {
        this.episodeDtoList = episodeDtoList;
    }
}
