package io.home.hibernateapp;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import io.home.hibernateapp.dto.UserDetails;

public class HibernateApp {

	public static void main(String[] args) {
		UserDetails user1 	= new UserDetails(1, "FIRST USER", 
				new Date(), "1st User's address", "1st user's description");
		
		UserDetails user2 	= new UserDetails(2, "SECOND USER", 
				new Date(), "2nd User's address", "2nd user's description");
		
		SessionFactory sessionFactory = new Configuration()
											.configure()
											.buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		session.save(user1);
		session.save(user2);
		session.getTransaction().commit();
		session.close();
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		UserDetails user = (UserDetails) session.get(UserDetails.class, 2);
		System.out.println("User name :  "+user.getUserName());
		
	}

}
