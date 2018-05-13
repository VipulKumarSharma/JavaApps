package jdbcapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jdbcapp.dao.JdbcAppDAOImpl;
import jdbcapp.model.Circle;

public class JdbcApp {

	public static void main(String[] args) {
		
		Circle circle = new JdbcAppDAOImpl().getCircle_usingJDBC(1);
		System.out.println("[getCircle_usingJDBC] "+circle.getName());

		ApplicationContext ctxt = new ClassPathXmlApplicationContext("spring.xml");
		JdbcAppDAOImpl dao = ctxt.getBean("jdbcAppDAOImpl", JdbcAppDAOImpl.class);
		
		System.out.println("[getCircle_usingSpringDataSource] "+dao.getCircle_usingSpringDataSource(1).getName());
	}

}
