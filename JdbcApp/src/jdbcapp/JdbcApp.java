package jdbcapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jdbcapp.dao.HibernateDAOImpl;
import jdbcapp.dao.JdbcAppDAOImpl;
import jdbcapp.dao.SimpleJdbcDAOImpl;
import jdbcapp.model.Circle;

public class JdbcApp {

	public static void main(String[] args) {
		
		Circle circle = new JdbcAppDAOImpl().getCircle_usingJDBC(1);
		System.out.println("[getCircle_usingJDBC] "+circle.getName());

		ApplicationContext ctxt = new ClassPathXmlApplicationContext("spring.xml");
		JdbcAppDAOImpl dao = ctxt.getBean("jdbcAppDAOImpl", JdbcAppDAOImpl.class);
		
		System.out.println("[getCircle_usingSpringDataSource] "+dao.getCircle_usingSpringDataSource(1).getName());
		
		System.out.println("[getCircle_usingDbcpDataSource] "+dao.getCircle_usingDbcpDataSource(1).getName());
		
		System.out.println("[getCircleCount] "+dao.getCircleCount());
		
		System.out.println("[getCircleName_usingJdbcTemplate] "+dao.getCircleName_usingJdbcTemplate(1));
		
		System.out.println("[getCircleById_usingJdbcTemplate] "+dao.getCircleById_usingJdbcTemplate(1));
		
		System.out.println("[getAllCircles_usingJdbcTemplate] "+dao.getAllCircles_usingJdbcTemplate());
		
		/*Circle circleObj = new Circle(2, "Second Circle");
		dao.insertCircleRecord_usingJdbcTemplate(circleObj);
		System.out.println("[getCircleCount] "+dao.getCircleCount());*/
	
		/*dao.createTraingleTable_usingJdbcTemplate();*/
		
		/*Triangle trianglObj = new Triangle(2, "Second Triangle");
		dao.insertTriangleRecord_usingNamedParameterJdbcTemplate(trianglObj);*/
		
		SimpleJdbcDAOImpl daoImpl = ctxt.getBean("simpleJdbcDAOImpl", SimpleJdbcDAOImpl.class);
		System.out.println("[getTriangleCount_usingJdbcTemplateSupportClass] "+daoImpl.getTriangleCount_usingJdbcTemplateSupportClass());
		
		/*HibernateDAOImpl hibernateDAOImpl = ctxt.getBean("hibernateDAOImpl", HibernateDAOImpl.class);
		System.out.println("[getSquareCount_usingHibernate] "+hibernateDAOImpl.getSquareCount_usingHibernate());*/
	}

}
