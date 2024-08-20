package com.ra.hn_jv231229_md03_watchfilmonline_project.dao.implementation;

import com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design.IBannerDao;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Banner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public class BannerDaoImpl implements IBannerDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Banner> findAll() {
        Session session = sessionFactory.openSession();
        try {
            List list = session.createQuery("from Banner", Banner.class).list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Banner findById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            Banner banner = (Banner) session.get(Banner.class, id);
            return banner;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Banner banner) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
                session.save(banner);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    @Override
    public void delete(Long bannerId) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(findById(bannerId));
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Banner banner) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(banner);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
