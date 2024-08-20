package com.ra.hn_jv231229_md03_watchfilmonline_project.controller;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.response.FilmDetailResponseDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Banner;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Film;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.FilmCategory;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.User;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.ICategoryService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.IFilmFavoriteService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.IFilmService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.implementation.BannerService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.implementation.FilmManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/listCategory")
public class CategoryController
{
    private ICategoryService categoryService;
    private IFilmFavoriteService filmFavoriteService;
    private FilmManagerService filmManagerService;
    private final IFilmService filmService;
    private final BannerService bannerService;
    private final HttpSession session;

    @Autowired
    public CategoryController(IFilmFavoriteService filmFavoriteService, FilmManagerService filmManagerService, IFilmService filmService, BannerService bannerService, ICategoryService categoryService, HttpSession session)
    {
        this.categoryService = categoryService;
        this.filmFavoriteService = filmFavoriteService;
        this.filmManagerService = filmManagerService;
        this.filmService = filmService;
        this.bannerService = bannerService;
        this.session = session;
    }

    @GetMapping
    public String listCategories(Model model,
                                 @RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "size", defaultValue = "5") int size,
                                 @RequestParam(value = "sort", defaultValue = "false") boolean sort,
                                 @RequestParam(value = "query", required = false) String query)
    {
        long totalCategories;
        if (query != null && !query.isEmpty())
        {
            model.addAttribute("categories", categoryService.search(query, page - 1, size));
            totalCategories = categoryService.countSearch(query);
        } else
        {
            if (sort)
            {
                model.addAttribute("categories", categoryService.findAllSortedAlphabetically(page - 1, size));
            } else
            {
                model.addAttribute("categories", categoryService.findAll(page - 1, size));
            }
            totalCategories = categoryService.countAllFilmCategory();
        }
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", (int) Math.ceil((double) totalCategories / size));
        model.addAttribute("sort", sort);
        model.addAttribute("query", query);
        return "/category/listFilmCategory";
    }

    @GetMapping("/addFilmCategory")
    public String showAddCategoryForm(Model model)
    {
        model.addAttribute("category", new FilmCategory());
        return "/category/addFilmCategory";
    }

    @PostMapping("/addFilmCategory")
    public String addCategory(@ModelAttribute("category") FilmCategory category)
    {
        categoryService.save(category);
        return "redirect:/listCategory";
    }

    @GetMapping("/{id}/edit")
    public String showEditCategoryForm(@PathVariable("id") Long id, Model model)
    {
        model.addAttribute("category", categoryService.findById(id));
        return "/category/editFilmCategory";
    }

    @PostMapping("/editFilmCategory/{id}")
    public String handleEditCategory(@ModelAttribute("category") FilmCategory filmCategory, @PathVariable("id") Long id)
    {
        filmCategory.setCategoryId(id);
        categoryService.update(filmCategory);
        return "redirect:/listCategory";
    }

    @GetMapping("/{id}/delete")
    public String deleteCategory(@PathVariable("id") Long id)
    {
        categoryService.deleteById(id);
        return "redirect:/listCategory";
    }

    @GetMapping("/user-category")
    public String userCategory(@RequestParam(value = "catId", required = false, defaultValue = "1") Long catId, Model model)
    {
        User user = (User) session.getAttribute("user");
        if (user == null)
        {
            return "redirect:/login";
        }
        List<Film> favoriteFilms = filmFavoriteService.getFavoritesFilm(user);
        model.addAttribute("username", user.getUsername());
        List<Film> allFilms = filmService.findAll();
        List<Banner> banners = bannerService.findAll();

        model.addAttribute("banners", banners);

        List<FilmDetailResponseDto> responseFilmList = new ArrayList<>();

        //ALl films
        for (Film film : allFilms)
        {
            responseFilmList.add(filmService.getResponseFilm(film));
        }

        model.addAttribute("filmList", responseFilmList);
        model.addAttribute("categoryList", categoryService.findAll(0, 1000));
        model.addAttribute("catId", catId);
//        Film film = filmManagerService.getFilmById(filmId);
//        filmFavoriteService.addFilmToFavorites(user, film);
        return "/category/category_film";
    }


//@GetMapping
//public String listAllCategories(Model model) {
//    List<FilmCategory> categories = categoryService.findALlNoPhanTrang();
//    model.addAttribute("categories", categories);
//    return "/category/listFilmCategory";
//}


}
