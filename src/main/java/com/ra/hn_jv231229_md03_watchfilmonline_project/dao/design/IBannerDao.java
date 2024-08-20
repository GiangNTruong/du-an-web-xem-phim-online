package com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IBannerDao {
    List<Banner> findAll();
    Banner findById(Long id);
    void save(Banner banner);
    void delete(Long bannerId);
    void update(Banner banner);
}
