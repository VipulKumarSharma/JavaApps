package io.home.hibernateapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import io.home.hibernateapp.dto.Account;
import io.home.hibernateapp.dto.Employee;
import io.home.hibernateapp.dto.Subject;
import io.home.hibernateapp.dto.Teacher;
import io.home.hibernateapp.dto.Vehicle;

public class HibernateMappings {

	public static void main(String[] args) {
		
		Vehicle vehicle 	= new Vehicle("AUDI");
		Employee employee 	= new Employee("Vipul Sharma", vehicle);
		
		Account account1	= new Account("V.K Sharma");
		Account account2	= new Account("Vipul Kumar Sharma");
		
		/* For One to Many relationship */
		employee.getAccounts().add(account1);
		employee.getAccounts().add(account2);

		/* For Many to One relationship */
		account1.setEmployee(employee);
		account2.setEmployee(employee);
		
		/****************************************************************************/
		SessionFactory sessionFactory = new Configuration()
											.configure()
											.buildSessionFactory();
		
		Session session 	= sessionFactory.openSession();
		
		/****************************************************************************/
		session.beginTransaction();
		session.save(employee);
		session.save(vehicle);
		session.save(account1);
		session.save(account2);
		session.getTransaction().commit();
		session.close();
		
		/****************************************************************************/
		
		Subject subject = new Subject("Maths");
		//Subject subject2 = new Subject("Science");
		
		Teacher teacher1 = new Teacher("Rajay");
		Teacher teacher2 = new Teacher("Gurmeet");
		
		subject.getTeachers().add(teacher1);
		subject.getTeachers().add(teacher2);
		
		teacher1.getSubjects().add(subject);
		teacher2.getSubjects().add(subject);
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(subject);
		session.save(teacher1);
		session.save(teacher2);
		
		session.getTransaction().commit();
		session.close();
	}

}
