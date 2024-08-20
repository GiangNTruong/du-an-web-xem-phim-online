package com.ra.hn_jv231229_md03_watchfilmonline_project.service.design;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Country;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.User;

import java.util.List;

public interface ICountryService {
    List<Country> displayWithPaginationAndOrder(String searchName,String order,Integer page,String direction);
    Country findById(Long id);
    void save(Country country);
    void delete(Long id);
    Long countCountry();
    List<Country> findAll();
}
