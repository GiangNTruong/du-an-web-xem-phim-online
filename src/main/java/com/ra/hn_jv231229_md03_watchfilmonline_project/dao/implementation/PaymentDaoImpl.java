package com.ra.hn_jv231229_md03_watchfilmonline_project.dao.implementation;

import com.ra.hn_jv231229_md03_watchfilmonline_project.dao.design.IPaymentDao;
import com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentDaoImpl implements IPaymentDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void save(Payment payment) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(payment);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	}
}
