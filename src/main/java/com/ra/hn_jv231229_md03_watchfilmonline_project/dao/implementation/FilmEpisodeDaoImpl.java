package com.ra.hn_jv231229_md03_watchfilmonline_project.dao.implementation;

import com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design.IFilmEpisodeDao;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.FilmEpisode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class FilmEpisodeDaoImpl implements IFilmEpisodeDao
{
    private final SessionFactory sessionFactory;

    @Autowired
    public FilmEpisodeDaoImpl(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Boolean saveEpisode(FilmEpisode filmEpisode)
    {
        Session session = sessionFactory.openSession();
        try
        {
            session.beginTransaction();
            if (filmEpisode.getFilmEpisodeId() == null)
            {
                session.save(filmEpisode);
            } else
            {
                session.update(filmEpisode);
            }
            session.getTransaction().commit();
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally
        {
            session.close();
        }
        return false;
    }

    @Override
    public FilmEpisode getEpisodeById(Long episodeId)
    {
        Session session = sessionFactory.openSession();
        try
        {
            return session.get(FilmEpisode.class, episodeId);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        } finally
        {
            session.close();
        }
    }

    @Override
    @Transactional
    public List<FilmEpisode> getEpisodeListByFilmId(long filmId)
    {
        Session session = sessionFactory.openSession();
        try
        {
            return session.createQuery("from FilmEpisode fe join fetch fe.film where fe.film.filmId = :filmId", FilmEpisode.class)
                    .setParameter("filmId", filmId)
                    .getResultList();
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        } finally
        {
            session.close();
        }
    }

    @Override
    public Boolean deleteEpisode(long id)
    {
        Session session = sessionFactory.openSession();
        try
        {
            session.beginTransaction();
            session.delete(session.get(FilmEpisode.class, id));
            session.getTransaction().commit();
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally
        {
            session.close();
        }
        return false;
    }

    @Override
    public String getFilmImageById(long id)
    {
        Session session = sessionFactory.openSession();
        try
        {
            return (String) session.createQuery("select filmEpisodeImage from FilmEpisode where filmEpisodeId = :id")
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        } finally
        {
            session.close();
        }
    }

    @Override
    public void deletePreviousEpisode(long filmId)
    {
        Session session = sessionFactory.openSession();
        try
        {
            session.beginTransaction();
            session.createQuery("delete from FilmEpisode where film.filmId = :id")
                    .setParameter("id", filmId).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e)
        {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally
        {
            session.close();
        }
    }

    @Override
    public FilmEpisode getById(Long episodeId)
    {
        Session session = sessionFactory.openSession();
        try
        {
            return session.get(FilmEpisode.class, episodeId);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        } finally
        {
            session.close();
        }
    }
}
