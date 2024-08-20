package com.ra.hn_jv231229_md03_watchfilmonline_project.service.design;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request.UserDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.User;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.request.UserFilterRequest;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.request.UserUpdateRoleRequest;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.request.UserUpdateStatusRequest;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.response.BaseResponse;
import com.ra.hn_jv231229_md03_watchfilmonline_project.util.Page;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

public interface IUserService
{
    public List<User> getAll();

    public User authenticate(String username, String password);

    public void register(User user);

    void updateUserStatus(UserUpdateStatusRequest request);

    public void updateUserRole(UserUpdateRoleRequest request);

    public BaseResponse<Page<UserDto>> getAllByFilter(UserFilterRequest filterRequest, int page, int size);

    List<User> getAllUsers();

    void update(UserDto userDto) throws ParseException;

    User findById(Long id);

    String getNewPassword(String username);

    Long countUser();

    void handleAddWallet(User user, Long money, HttpSession session);

    boolean handleUpdateAcc(User user, String option);

    void addHistory(Long idEpisode, User user);
}
