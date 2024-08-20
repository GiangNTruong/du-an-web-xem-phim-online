package com.ra.hn_jv231229_md03_watchfilmonline_project.controller;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request.UserDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Film;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.FilmEpisode;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.User;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.mapper.UserMapper;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.IUserService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private HttpSession session;
    @Autowired
    private IUserService userService;

    @GetMapping("/infor")
    public String infor(Model model)
    {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "user/infor-user";
    }

    @GetMapping("/edit")
    public String edit(Model model)
    {
        User user = (User) session.getAttribute("user");
        UserDto userDto = UserMapper.toEditDto(user);
        model.addAttribute("userDto", userDto);
        return "user/edit-user";
    }

    @PostMapping("/edit")
    public String edit(
            @RequestParam("userId") Long userId,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("fullName") String fullName,
            @RequestParam("fileAvatar") MultipartFile fileAvatar) throws ParseException
    {
        UserDto userDto = new UserDto(userId, fullName, email, phone, fileAvatar);
        userService.update(userDto);
        session.setAttribute("user", userService.findById(userDto.getUserId()));
        return "redirect:/user/infor";
    }

    @GetMapping("/addWallet")
    public String addWallet(Model model)
    {
        return "user/addWallet";
    }

    @PostMapping("/handleAddMoney")
    public String handleAddMoney(@RequestParam("money") Long money, Model model)
    {
        User user = (User) session.getAttribute("user");
        if (money < 0)
        {
            model.addAttribute("error", "money must be than 0");
            return "user/addWallet";
        }
        userService.handleAddWallet(user, money, session);
        return "redirect:/user/home";
    }

    @GetMapping("/viewUpdateAcc")
    public String viewUpdateAcc(Model model)
    {
        return "user/updateAccount";
    }

    @PostMapping("/handleUpdateAcc")
    public String handleUpdateAcc(@RequestParam("option") String option, HttpSession session, Model model)
    {
        User user = (User) session.getAttribute("user");
        boolean check = userService.handleUpdateAcc(user, option);
        if (check)
        {
            return "redirect:/user/home";
        } else
        {
            model.addAttribute("error", "you do not have enough money");
            return "user/updateAccount";
        }
    }

    @GetMapping("/history")
    public String history(Model model)
    {
        User user = (User) session.getAttribute("user");
        if (user == null)
        {
            return "redirect:/login";
        }
        Set<FilmEpisode> filmEpisodes = user.getFilmEpisodeSet();
        model.addAttribute("filmEpisodes", filmEpisodes);
        return "user/history-user";
    }

    @GetMapping("/home")
    public String home(Model model)
    {
        return "redirect:/";
    }

}
