package com.ra.hn_jv231229_md03_watchfilmonline_project.service.implementation;

import com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design.IBannerDao;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request.BannerDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Banner;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Film;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.IBannerService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.service.design.IFilmService;
import com.ra.hn_jv231229_md03_watchfilmonline_project.util.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class BannerService implements IBannerService
{
    private IBannerDao bannerDao;
    private IFilmService filmService;
    private FileUploadService fileUploadService;

    @Autowired
    public BannerService(IBannerDao bannerDao, IFilmService filmService, FileUploadService fileUploadService)
    {
        this.bannerDao = bannerDao;
        this.filmService = filmService;
        this.fileUploadService = fileUploadService;
    }

    @Override
    public List<Banner> findAll()
    {
        return bannerDao.findAll();
    }

    @Override
    public Banner findById(Long id)
    {
        return bannerDao.findById(id);
    }

    @Override
    public void save(Long filmId, MultipartFile file)
    {
        Banner banner = new Banner();
        banner.setBannerImage(fileUploadService.uploadFileToServer(file));
        banner.setFilm(filmService.getFilmById(filmId));
        bannerDao.save(banner);
    }

    @Override
    public void delete(Long bannerId)
    {
        Banner banner = findById(bannerId);
        if (banner.getFilm() != null)
        {
            bannerDao.delete(bannerId);
        } else
        {
            banner.setBannerImage("https://resources.alleghenycounty.us/css/images/Default_No_Image_Available.png");
            bannerDao.update(banner);
        }
    }

    @Override
    public void update(BannerDto bannerDto)
    {
        MultipartFile file = bannerDto.getBannerImageFile();
        Banner banner = findById(bannerDto.getBannerId());
        banner.setFilm(bannerDto.getFilm());
        if (file != null && file.getSize() > 0)
        {
            banner.setBannerImage(fileUploadService.uploadFileToServer(file));
        }
        bannerDao.update(banner);
    }
}
