package com.ra.hn_jv231229_md03_watchfilmonline_project.controller;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request.BannerDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Banner;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Country;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Film;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.*;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController
{
    private IBannerService bannerService;
    private IFilmService filmService;
    private IUserService userService;
    private ICategoryService categoryService;
    private ICommentService commentService;

    @Autowired
    public AdminController(IBannerService bannerService, IFilmService filmService, ICategoryService categoryService, IUserService userService, ICommentService commentService)
    {
        this.bannerService = bannerService;
        this.filmService = filmService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model)
    {
        model.addAttribute("countUser",userService.countUser());
        model.addAttribute("countAllFilmCategories", categoryService.countAllFilmCategories());
        model.addAttribute("averageRating", commentService.averageRating());
        model.addAttribute("countView", filmService.countView());
        return "admin/dashboard";
    }

    @GetMapping("/banner")
    public String banner(Model model)
    {
        List<Banner> banners = bannerService.findAll();
        List<Film> films = filmService.findAll();
        model.addAttribute("films", films);
        model.addAttribute("banners", banners);
        model.addAttribute("bannerAdd", new Banner());
        return "banner/list-banner";
    }

    @PostMapping("/banner/add")
    public String add(Model model, @RequestParam("filmId") Long filmId,@RequestParam("file") MultipartFile file) {
        bannerService.save(filmId, file);
        return "redirect:/admin/banner";
    }

    @GetMapping("/banner/initedit/{id}")
    public String initedit(Model model, @PathVariable Long id)
    {
        Banner banner = bannerService.findById(id);
        BannerDto bannerDto = new BannerDto();
        bannerDto.setBannerId(banner.getBannerId());
        bannerDto.setBannerImageUrl(banner.getBannerImage());
        bannerDto.setFilm(banner.getFilm());
        model.addAttribute("bannerDto", bannerDto);
        List<Film> films = filmService.findAll();
        model.addAttribute("films", films);
        return "banner/edit-banner";
    }

    @PostMapping("/banner/edit")
    public String edit(@ModelAttribute("bannerDto") BannerDto bannerDto, @RequestParam("filmId") Long filmId)
    {
        bannerDto.setFilm(filmService.getFilmById(filmId));
        bannerService.update(bannerDto);
        return "redirect:/admin/banner";
    }

    @GetMapping("/banner/delete/{id}")
    public String delete(@PathVariable Long id)
    {
        bannerService.delete(id);
        return "redirect:/admin/banner";
    }

}
