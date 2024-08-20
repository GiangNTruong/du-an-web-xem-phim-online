package com.ra.hn_jv231229_md03_watchfilmonline_project.service.implementation;

import com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design.IFilmEpisodeDao;
import com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design.IPaymentDao;
import com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design.IUserDao;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.constant.SubscriptionType;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.constant.UserRole;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request.UserDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.FilmEpisode;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Payment;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.User;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.request.UserFilterRequest;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.request.UserUpdateRoleRequest;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.request.UserUpdateStatusRequest;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.response.BaseResponse;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.IUserService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


import com.ra.hn_jv231229_md03_watchfilmonline_project.util.FileUploadService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;

@Service
public class UserService implements IUserService
{
    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IPaymentDao paymentDao;
    @Autowired
    private IFilmEpisodeDao episodeDao;

    @Autowired
    public UserService(IUserDao userDao)
    {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAll()
    {
        return userDao.getAll();
    }

    public User authenticate(String username, String password)
    {
        return userDao.authenticate(username, password);
    }

    @Override
    public void register(User user)
    {
        user.setUserRole(UserRole.FREE);
        user.setWallet_balance(0L);
        userDao.register(user);
    }

    @Override
    public void updateUserStatus(UserUpdateStatusRequest request)
    {
        userDao.updateStatus(request);
    }

    @Override
    public void updateUserRole(UserUpdateRoleRequest request)
    {
        userDao.updateRole(request);
    }

    @Override
    public BaseResponse<Page<UserDto>> getAllByFilter(UserFilterRequest filterRequest, int page, int size)
    {
        Page<UserDto> pageUser = userDao.getAllByFilter(filterRequest, page, size);

        BaseResponse<Page<UserDto>> response = new BaseResponse<>();
        response.setCode(200);
        response.setMessage("success");
        response.setData(pageUser);
        return response;
    }

    @Override
    public List<User> getAllUsers()
    {
        return userDao.getAllUsers();
    }


    @Override
    public void update(UserDto userDto) throws ParseException
    {
        MultipartFile file = userDto.getFileAvatar();
        User user = findById(userDto.getUserId());

        user.setUpdatedAt(new Date());
        if (file.getSize() > 0 && file != null)
        {
            user.setAvatar(fileUploadService.uploadFileToServer(file));
        } else
        {
            user.setAvatar(findById(user.getUserId()).getAvatar());
        }
        userDao.update(user);
    }

    @Override
    public User findById(Long id)
    {
        return userDao.findById(id);
    }

    @Override
    public String getNewPassword(String username)
    {
        User user = userDao.findByUsername(username);
        if (user != null)
        {
            String newPassword = getRandomPassword();
            user.setPassword(newPassword);
            userDao.update(user);
            return newPassword;
        }
        return null;
    }

    public String getRandomPassword()
    {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 6; i++)
        {
            str.append(Math.round(Math.random() * 10));
        }
        return str.toString();
    }

    @Override
    public Long countUser()
    {
        return userDao.countUser();
    }

    @Override
    public void handleAddWallet(User user, Long money, HttpSession session)
    {
        user.setWallet_balance(user.getWallet_balance() + money);
        session.setAttribute("user", user);
        userDao.update(user);
    }

    @Override
    public boolean handleUpdateAcc(User user, String option)
    {
        Payment payment = new Payment();
        payment.setUser(user);
        payment.setPaymentDate(new Date());
        switch (option)
        {
            case "MONTHLY - 100.000đ":
                if (user.getWallet_balance() < 100000)
                {
                    return false;
                }
                user.setWallet_balance(user.getWallet_balance() - 100000);
                payment.setSubscriptionType(SubscriptionType.MONTHLY);
                break;
            case "HALF_YEAR - 500.000đ":
                if (user.getWallet_balance() < 500000)
                {
                    return false;
                }
                user.setWallet_balance(user.getWallet_balance() - 500000);
                payment.setSubscriptionType(SubscriptionType.HALF_YEAR);
                break;
            case "YEARLY - 1.000.000đ":
                if (user.getWallet_balance() < 1000000)
                {
                    return false;
                }
                user.setWallet_balance(user.getWallet_balance() - 1000000);
                payment.setSubscriptionType(SubscriptionType.YEARLY);
                break;
            default:
                return false;
        }
        user.setUserRole(UserRole.PAID);
        userDao.update(user);
        paymentDao.save(payment);
        return true;
    }

    @Override
    public void addHistory(Long idEpisode, User user)
    {
        FilmEpisode episode = episodeDao.getById(idEpisode);
        Set<FilmEpisode> episodeList = user.getFilmEpisodeSet();
        episodeList.add(episode);
        user.setFilmEpisodeSet(episodeList);
    }
}
