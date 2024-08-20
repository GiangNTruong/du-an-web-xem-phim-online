package com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Country;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICountryDao {
    List<Country> displayWithPaginationAndOrder(String searchName,Integer page);
    Country findById(Long id);
    void save(Country country);
    void delete(Long id);
    Long countCountry();
    List<Country> findAll();

}

