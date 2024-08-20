package com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.FilmCategory;

import java.util.List;

public interface ICategoryDao {

    List<FilmCategory> findAll(int currentPage, int size);


    FilmCategory findById(Long id);


    void save(FilmCategory filmCategory);


    void deleteById(Long id);

    void update(FilmCategory filmCategory);

    // Sắp xếp chủ đề phim theo bảng chữ cái
    List<FilmCategory> findAllSortedAlphabetically(int currentPage,int size);


    List<FilmCategory> search(String keyword,int currentPage, int size);

    Long countAllFilmCategory();

    Long countSearch(String query);

//    List<FilmCategory> findALlNoPhanTrang();
    Long countAllFilmCategories();
}
