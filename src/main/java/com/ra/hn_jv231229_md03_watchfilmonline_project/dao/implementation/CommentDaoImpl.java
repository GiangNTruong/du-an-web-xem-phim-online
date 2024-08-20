package com.ra.hn_jv231229_md03_watchfilmonline_project.dao.implementation;

import com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design.ICommentDao;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentDaoImpl implements ICommentDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Comment> findAll(int currentPage, int size) {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("select c from Comment c join c.film f join c.user u", Comment.class)
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
    public void addComment(Comment comment) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(comment);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public List<Comment> findByContentContaining(String content) {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from Comment c where c.content like :content", Comment.class)
                    .setParameter("content", "%" + content + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Comment comment = session.get(Comment.class, id);
            if (comment != null) {
                session.delete(comment);
                session.getTransaction().commit();
            } else {
                throw new RuntimeException("Comment not found");
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }


    @Override
    public Long countAllComment() {
        Session session = sessionFactory.openSession();
        try {
            return (Long) session.createQuery("select count(*) from Comment").uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }




    @Override
    public List<Comment> findAllSorted() {
        Session session = sessionFactory.openSession();
        List<Comment> comments = new ArrayList<>();
        try {
            String hql = "FROM Comment c ORDER BY c.stars ASC";
            Query query = session.createQuery(hql);
            comments = query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return comments;
    }

    @Override
    public List<Comment> findCommentByFilm(Long filmId)
    {
        Session session = sessionFactory.openSession();
        try
        {
            return session.createQuery("from Comment where film.filmId = :filmId", Comment.class)
                    .setParameter("filmId", filmId).getResultList();
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        } finally
        {
            session.close();
        }
    }

    @Override
    public Double averageRating() {
        Session session = sessionFactory.openSession();
        try {
            Double count = (Double) session.createQuery("select avg (stars) from Comment").uniqueResult();
            return count == null ? 0 : count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

}