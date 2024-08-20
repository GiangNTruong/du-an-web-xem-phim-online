package com.ra.hn_jv231229_md03_watchfilmonline_project.controller;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Country;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.implementation.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/country")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping("")
    public String list(@RequestParam(value = "name", required = false)String name,
                       @RequestParam(value = "order", required = false) String order,
                       @RequestParam(value = "page", defaultValue = "1") Integer page,Model model,
                       @RequestParam(value = "direction", required = false,defaultValue = "asc") String direction) {
        List<Country> countries = countryService.displayWithPaginationAndOrder(name,order,page,direction);
        Integer totalPage = (int) Math.ceil((double) countryService.countCountry()/5);
        if (totalPage < 1) {
            totalPage = 1;
        }
        Boolean directionCheck = true;
        if (direction.equals("asc")) {
            directionCheck = false;
        }
        model.addAttribute("directionCheck", directionCheck);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("curr",page);
        model.addAttribute("countryAdd", new Country());
        model.addAttribute("countries", countries);
        return "country/list-country";
    }

    @PostMapping("/add")
    public String add(@Validated @ModelAttribute("countryAdd") Country country, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "country/list-country";
        } else {
            countryService.save(country);
            return "redirect:/admin/country";
        }
    }

    @GetMapping("/initedit/{id}")
    public String initAdd(@PathVariable Long id, Model model) {
        Country country = countryService.findById(id);
        model.addAttribute("country", country);
        return "country/edit-country";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("country") Country country) {
        countryService.save(country);
        return "redirect:/admin/country";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        countryService.delete(id);
        return "redirect:/admin/country";
    }
}

