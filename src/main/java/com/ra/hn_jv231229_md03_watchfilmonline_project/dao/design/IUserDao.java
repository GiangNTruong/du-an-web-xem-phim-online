package com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request.UserDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.User;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.request.UserFilterRequest;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.request.UserUpdateRoleRequest;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.request.UserUpdateStatusRequest;
import com.ra.hn_jv231229_md03_watchfilmonline_project.util.Page;

import java.util.List;

public interface IUserDao
{
    public List<User> getAll();

    public User authenticate(String username, String password);

    public void register(User user);

    Page<UserDto> getAllByFilter(UserFilterRequest filterRequest, int page, int size);

    void updateStatus(UserUpdateStatusRequest request);

    void updateRole(UserUpdateRoleRequest request);

    User findById(Long id);

    List<User> getAllUsers();

    void update(User user);

    User findByUsername(String username);


    Long countUser();
}
