package com.ra.hn_jv231229_md03_watchfilmonline_project.dao.implementation;

import com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design.IFavoriteFilmDao;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Film;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class FilmFavoriteDaoImpl implements IFavoriteFilmDao {
    @Autowired
    private  SessionFactory sessionFactory;
    @Autowired
    private UserDaoImpl userDao;
//    @Override
//    public void addFIlmToFavorites(User user, Film film){
//        Session session = sessionFactory.openSession();;
//        try {
//            session.beginTransaction();
//            user.getFilmSet().add(film);
//            userDao.update(user);
//            session.getTransaction().commit();
//        }catch (Exception e){
//            session.getTransaction().rollback();
//            throw  new RuntimeException();
//        }finally {
//            session.close();
//        }
//    }

//    @Override
//    public void addFIlmToFavorites(User user, Film film) {
//        Session session;
//        if(sessionFactory.getCurrentSession()!=null)
//            session = sessionFactory.getCurrentSession();
//        else
//            session = sessionFactory.openSession();
//        try {
//            session.beginTransaction();
//            user.getFilmSet().add(film);
//            session.update(user);
//            session.getTransaction().commit();
//        }catch (Exception e){
//            session.getTransaction().rollback();
//            throw new RuntimeException(e);
//        }finally {
//            session.close();
//        }
//    }

    @Override
    public void removeFilmFromFavorites(User user, Film film) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            user.setFilmSet(user.getFilmSet().stream().filter(item -> !Objects.equals(item.getFilmId(), film.getFilmId())).collect(Collectors.toSet()));
            userDao.update(user);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }

    @Override
    public List<Film> getFavoritesFilm(User user) {
        Session session = sessionFactory.openSession();
        try {
            user = session.get(User.class,user.getUserId());
            return new ArrayList<>(user.getFilmSet());
        }finally {
            session.close();
        }
    }
}
