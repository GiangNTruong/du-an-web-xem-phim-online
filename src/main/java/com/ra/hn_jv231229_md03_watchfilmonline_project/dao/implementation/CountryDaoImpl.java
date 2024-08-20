package com.ra.hn_jv231229_md03_watchfilmonline_project.dao.implementation;

import com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design.ICountryDao;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Country;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Film;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class CountryDaoImpl implements ICountryDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Country> displayWithPaginationAndOrder(String searchName ,Integer page) {
        Session session = sessionFactory.openSession();
        List<Country> list = null;
        try {
            if (searchName != null && !searchName.isEmpty()) {
                list = session.createQuery("from Country where countryName like: countryName", Country.class)
                        .setParameter("countryName", "%"+searchName+"%")
                        .list();
            } else {
                list = session.createQuery("from Country", Country.class).
                        setFirstResult((page-1)*5)
                        .setMaxResults(5)
                        .list();
            }

            list.forEach(country -> country.setFilmList(session.createQuery("from Film where country.countryId=:country_id", Film.class)
                    .setParameter("country_id",country.getCountryId())
                    .list()));
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Country findById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            Country country = session.get(Country.class, id);
            return country;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public void  save(Country country) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            if (country.getCountryId()==null){
                session.save(country);
            } else {
                session.update(country);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Long countCountry() {
        Session session = sessionFactory.openSession();
        Long count = session.createQuery("select count(*) from Country", Long.class).uniqueResult();
        return count;
    }

    @Override
    public List<Country> findAll() {
        Session session = sessionFactory.openSession();
        try {
            List<Country> list = session.createQuery("from Country", Country.class).list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
}

