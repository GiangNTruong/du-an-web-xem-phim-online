package com.ra.hn_jv231229_md03_watchfilmonline_project.controller;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request.UserDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.request.UserFilterRequest;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.response.BaseResponse;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.implementation.UserService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.util.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userList")
public class UserResource
{

    private final UserService service;

    public UserResource(UserService service)
    {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse<Page<UserDto>>> getAll(@RequestBody UserFilterRequest filterRequest,
                                                              @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                                              @RequestParam(name = "size", required = false, defaultValue = "3") int size)
    {
        System.out.println(filterRequest);
        System.out.println(page);
        System.out.println(size);

        return ResponseEntity.ok(service.getAllByFilter(filterRequest, page, size));
    }
}
