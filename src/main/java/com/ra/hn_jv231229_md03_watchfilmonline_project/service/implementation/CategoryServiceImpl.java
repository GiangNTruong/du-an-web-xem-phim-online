package com.ra.hn_jv231229_md03_watchfilmonline_project.service.implementation;


import com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design.ICategoryDao;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.FilmCategory;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private ICategoryDao categoryDao;

    @Override
    public List<FilmCategory> findAll(int currentPage, int size) {
        return categoryDao.findAll(currentPage, size);
    }

    @Override
    public FilmCategory findById(Long id) {
        return categoryDao.findById(id);
    }

    @Override
    public void save(FilmCategory filmCategory) {
        categoryDao.save(filmCategory);
    }

    @Override
    public void deleteById(Long id) {
        categoryDao.deleteById(id);
    }

    @Override
    public void update(FilmCategory filmCategory) {
        categoryDao.update(filmCategory);
    }

    @Override
    public List<FilmCategory> findAllSortedAlphabetically(int currentPage, int size) {
        return categoryDao.findAllSortedAlphabetically(currentPage, size);
    }

    @Override
    public List<FilmCategory> search(String keyword,int currentPage, int size) {
        return categoryDao.search(keyword,currentPage,size);
    }

    @Override
    public Long countAllFilmCategory() {
        return categoryDao.countAllFilmCategory();
    }

    @Override
    public long countSearch(String query) {
        return categoryDao.countSearch(query);
    }

    @Override
    public Long countAllFilmCategories() {
        return categoryDao.countAllFilmCategories();
    }

//    @Override
//    public List<FilmCategory> findALlNoPhanTrang() {
//        return categoryDao.findALlNoPhanTrang();
//    }
}