package com.ra.hn_jv231229_md03_watchfilmonline_project.service.design;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.dto.request.BannerDto;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IBannerService {
    List<Banner> findAll();
    Banner findById(Long id);
    void save(Long filmId, MultipartFile file);
    void delete(Long bannerId);
    void update(BannerDto bannerDto);
}
