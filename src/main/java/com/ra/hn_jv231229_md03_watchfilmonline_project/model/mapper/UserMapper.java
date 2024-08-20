package com.ra.hn_jv231229_md03_watchfilmonline_project.model.mapper;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request.UserDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.User;

public class UserMapper
{
    public static UserDto toUserDTO(User user)
    {
        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getUserRole().name(),
                user.getCreatedAt(),
                user.getStatus()
        );
    }
    public static UserDto toEditDto(User user){
        return new UserDto(
                user.getUserId(),
                user.getFullname(),
                user.getEmail(),
                user.getPhone(),
                user.getAvatar()
        );
    }
}

