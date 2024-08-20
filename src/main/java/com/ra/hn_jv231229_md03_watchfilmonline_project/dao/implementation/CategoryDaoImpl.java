package com.ra.hn_jv231229_md03_watchfilmonline_project.dao.implementation;


import com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design.ICategoryDao;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.FilmCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl implements ICategoryDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<FilmCategory> findAll(int currentPage, int size) {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from FilmCategory", FilmCategory.class)
                    .setFirstResult(currentPage*size)
                    .setMaxResults(size)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public FilmCategory findById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            return session.find(FilmCategory.class, id);
        }catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void save(FilmCategory filmCategory) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(filmCategory);
            session.getTransaction().commit();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void update(FilmCategory filmCategory) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(filmCategory);
            session.getTransaction().commit();
        }catch (Exception e){
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<FilmCategory> findAllSortedAlphabetically(int currentPage, int size) {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from FilmCategory  order by categoryName", FilmCategory.class)
                    .setFirstResult(currentPage*size)
                    .setMaxResults(size)
                    .getResultList();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }


    @Override
    public List<FilmCategory> search(String keyword,int currentPage, int size) {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from FilmCategory where categoryName like :keyword", FilmCategory.class)
                    .setParameter("keyword", "%" + keyword + "%")
                    .getResultList();
        } finally {
            session.close();
        }
    }

    @Override
    public Long countAllFilmCategory() {
        Session session = sessionFactory.openSession();
        try {
            return (Long) session.createQuery("select count(fc.categoryId) from FilmCategory fc").getSingleResult();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public Long countSearch(String keyword) {
        Session session = sessionFactory.openSession();
        try {
            return (Long) session.createQuery("select count(fc.categoryId) from FilmCategory fc where fc.categoryName like :keyword")
                    .setParameter("keyword", "%" + keyword + "%")
                    .getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public Long countAllFilmCategories() {
        Session session = sessionFactory.openSession();
        try {
            Long count = (Long) session.createQuery("select count(*) from FilmCategory ").uniqueResult();
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

//    @Override
//    public List<FilmCategory> findALlNoPhanTrang() {
//        Session session = sessionFactory.openSession();
//        try {
//            List list = session.createQuery("from FilmCategory ").list();
//            return list;
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            session.close();
//        }
//        return null;
//    }

}
