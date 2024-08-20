package com.ra.hn_jv231229_md03_watchfilmonline_project.controller;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request.CommentDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Comment;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Film;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.User;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.ICommentService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.IFilmService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController
{
    @Autowired
    private ICommentService commentService;
    @Autowired
    private IFilmService filmService;
    @Autowired
    private IUserService userService;
    @Autowired
    private HttpSession session;

    @GetMapping
    public String getAllComments(Model model,
                                 @RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "3") int size)
    {
        List<Comment> comments = commentService.findAll(page - 1, size);
        Long totalComments = commentService.countAllComment();
        int totalPages = (int) Math.ceil((double) totalComments / size);
        model.addAttribute("page", page);
        model.addAttribute("comments", comments);
        model.addAttribute("totalPages", totalPages);
        return "comment/listCommentFilm";
    }

//    @GetMapping("/add")
//    public String showAddCommentForm(Model model) {
//        List<Film> films = filmService.getAllFilms(0,1000);
//        List<User> users = userService.getAllUsers();
//        model.addAttribute("films", films);
//        model.addAttribute("users", users);
//        model.addAttribute("commentDto", new CommentDto());
//        return "comment/addCommentFilm";
//    }

    @PostMapping("/add")
    public String addComment(@ModelAttribute("commentDto") CommentDto commentDto)
    {
        User user = (User) session.getAttribute("user");
        if (user == null)
        {
            return "redirect:/login";
        }
        commentService.addComment(commentDto);
        return "redirect:/movie-detail/" + commentDto.getFilmId();
    }


    @GetMapping("/{id}/delete")
    public String deleteComment(@PathVariable("id") Long id)
    {
        commentService.deleteById(id);
        return "redirect:/comments";
    }


    @GetMapping("/search")
    public String searchComments(@RequestParam String content, Model model)
    {
        List<Comment> comments = commentService.findByContentContaining(content);
        model.addAttribute("comments", comments);
        return "comment/listCommentFilm";
    }

    @GetMapping("/sorted")
    public String getSortedComments(Model model)
    {
        List<Comment> comments = commentService.findAllSorted();
        model.addAttribute("comments", comments);
        return "comment/listCommentFilm";
    }

    @GetMapping("/listCommentByFilm/{id}")
    public String getListCommentByFilm(Model model, @PathVariable("id") Long id, HttpSession session)
    {
        model.addAttribute("listComment", commentService.findCommentByFilm(id));
        model.addAttribute("filmId", id);
        session.getAttribute("userId");
        return "movie-details";
    }


}
