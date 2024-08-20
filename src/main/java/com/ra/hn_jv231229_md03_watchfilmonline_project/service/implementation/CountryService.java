package com.ra.hn_jv231229_md03_watchfilmonline_project.service.implementation;

import com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design.ICountryDao;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Country;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService implements ICountryService {
    @Autowired
    private ICountryDao countryDao;

    @Override
    public List<Country> displayWithPaginationAndOrder(String searchName, String order, Integer page, String direction) {
        List<Country> list = countryDao.displayWithPaginationAndOrder(searchName, page);
        if (order != null && !order.isEmpty()) {
            if (direction != null && direction.equals("asc")) {
                switch (order) {
                    case "id":
                        list = list.stream().sorted(Comparator.comparingLong(Country::getCountryId)).collect(Collectors.toList());
                        break;
                    case "name":
                        list = list.stream().sorted(Comparator.comparing(Country::getCountryName)).collect(Collectors.toList());
                        break;
                    case "film":
                        list = list.stream().sorted((o1, o2) -> o1.getFilmList().size() - o2.getFilmList().size()).collect(Collectors.toList());
                        break;
                    default:
                        break;
                }
            } else {
                switch (order) {
                    case "id":
                        list = list.stream().sorted(Comparator.comparingLong(Country::getCountryId).reversed()).collect(Collectors.toList());
                        break;
                    case "name":
                        list = list.stream().sorted(Comparator.comparing(Country::getCountryName).reversed()).collect(Collectors.toList());
                        break;
                    case "film":
                        list = list.stream().sorted((o1, o2) -> o2.getFilmList().size() - o1.getFilmList().size()).collect(Collectors.toList());
                        break;
                    default:
                        break;
                }
            }

        }
        return list;
    }

    @Override
    public Country findById(Long id) {
        return countryDao.findById(id);
    }

    @Override
    public void save(Country country) {
        countryDao.save(country);
    }
    @Override
    public void delete(Long id) {
        Country country = findById(id);
        if (country.getFilmList().isEmpty()) {
            countryDao.delete(id);
        } else {
            country.setCountryName("N/A");
            save(country);
        }
    }

    @Override
    public Long countCountry() {
        return countryDao.countCountry();
    }

    @Override
    public List<Country> findAll() {
        return countryDao.findAll();
    }
}
