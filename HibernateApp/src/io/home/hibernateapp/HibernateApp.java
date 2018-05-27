package io.home.hibernateapp;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import io.home.hibernateapp.dto.UserDetails;

public class HibernateApp {

	public static void main(String[] args) {
		UserDetails user = new UserDetails(1, "FIRST USER", 
				new Date(), "1st User's address", "1st user's description");
		
		SessionFactory sessionFactory = new Configuration()
											.configure()
											.buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}

}
