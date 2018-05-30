package io.home.hibernateapp;

import java.util.Date;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;

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
		
		/* Hibernate uses strategy to fetch collection object values.
		 * 
		 * LAZY INITIALIZATION STRATEGY - only initialize 1st level variables (uses PROXY Class).
		 * 
		 * Whenever we are using session.get(), by default it only fetches the values of fields inside the object,
		 * NOT the values of the Embedded Collection Object.
		 * 
		 * Embedded Collection Object values are fetched from DB, when we call get() over that collection object
		 * (i.e. user.getAddressesOverTheYears())
		 * 
		 * Hibernate by default instead of giving you the actual object of the required class, it provide 
		 * an object of PROXY class.
		 * PROXY Class - dynamic subclass of object you are fetching.
		 * It has same methods as in actual required class.
		 * Whenever you call a get method, it calls the parent class method.
		 * When we call get() on Collection object it first gets the data from DB, then calls parent class Collection object get().
		 * 
		 * If we closes the session before calling Collection object get(), it will give an exception.
		 * (i.e. LazyInitializationException)
		 * 
		 * 
		 * You can EAGERLY fetch all the data at one time by overriding default behavior.
		 * That's gonna take a lot of resources and time.
		 * (i.e @ElementCollection(fetch=FetchType.EAGER))
		 * 
		 * It also provides proxy object (Why?)
		 * It could be another collection object on which we haven't provide EAGER
		 */
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		UserDetails user = (UserDetails) session.get(UserDetails.class, 1);
		System.err.println("User name : "+user.getUserName());
		System.err.println("User home address : "+user.getHomeAddress().getState()+"\n");
		
		session.close();
		System.err.println("[EAGER Fetching] No. of past addresses : "+user.getAddressesOverTheYears().size());
	}

}
