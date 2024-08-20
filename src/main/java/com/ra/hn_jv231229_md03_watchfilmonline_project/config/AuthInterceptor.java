package com.ra.hn_jv231229_md03_watchfilmonline_project.config;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.constant.UserRole;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null) {
            if(user.getUserRole().equals(UserRole.ADMIN)) {
                return true;
            } else {
                response.sendRedirect("/403");
                return false;
            }
        } else {
            response.sendRedirect("/login");
            return false;
        }
    }
}
