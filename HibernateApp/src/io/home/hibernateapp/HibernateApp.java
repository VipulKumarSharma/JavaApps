package io.home.hibernateapp;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import io.home.hibernateapp.dto.Address;
import io.home.hibernateapp.dto.UserDetails;

public class HibernateApp {

	public static void main(String[] args) {
		UserDetails user1 		= new UserDetails(1, "FIRST USER", new Date(), "1st User's address", "1st user's description");
		Address homeAddress 	= new Address("Street 1", "Dwarka", "New Delhi", "110059");
		Address address1 		= new Address("Past Address Street 1", "Delhi", "New Delhi", "110052");
		Address address2 		= new Address("Past Address Street 2", "Dehradun", "Uttrakhand", "885455");
		
		user1.setHomeAddress(homeAddress);
		user1.getAddressesOverTheYears().add(address1);
		user1.getAddressesOverTheYears().add(address2);
		
		
		/*UserDetails user2 		= new UserDetails(2, "SECOND USER", new Date(), "2nd User's address", "2nd user's description");
		Address officeAddress 	= new Address("Noida Street", "Noida", "U.P", "201301");
		user2.setOfficeAddress(officeAddress);*/
		
		/****************************************************************************/
		SessionFactory sessionFactory = new Configuration()
											.configure()
											.buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		/****************************************************************************/
		session.beginTransaction();
		session.save(user1);
		//session.save(user2);
		session.getTransaction().commit();
		session.close();
		
		/****************************************************************************/
		/*session = sessionFactory.openSession();
		session.beginTransaction();
		UserDetails user = (UserDetails) session.get(UserDetails.class, 1);
		System.err.println("\nUser name :  "+user.getUserName());*/
		
	}

}
