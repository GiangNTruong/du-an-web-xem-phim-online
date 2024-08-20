package com.ra.hn_jv231229_md03_watchfilmonline_project.controller;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.User;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.request.UserUpdateRoleRequest;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.request.UserUpdateStatusRequest;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController
{
    private final IUserService userService;

    @Autowired
    public AdminUserController(IUserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("")
    public String page(Model model)
    {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "admin/user/list-user";
    }

    @PostMapping("/updateStatus")
    @ResponseBody
    public ResponseEntity<String> updateUserStatus(@RequestBody UserUpdateStatusRequest request)
    {
        try
        {
            userService.updateUserStatus(request);
            return ResponseEntity.ok("Update successful");
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating status: " + e.getMessage());
        }
    }

    @PostMapping("/updateRole")
    @ResponseBody
    public ResponseEntity<String> updateUserRole(@RequestBody UserUpdateRoleRequest request)
    {
        try
        {
            userService.updateUserRole(request);
            return ResponseEntity.ok("Update successful");
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating status: " + e.getMessage());
        }
    }


}