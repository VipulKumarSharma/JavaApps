package jdbcapp.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//@Repository
public class HibernateDAOImpl {

	//@Autowired
	private SessionFactory sessionFactory;
	
	public int getSquareCount_usingHibernate() {
		String hql = "SELECT COUNT(*) FROM SQUARE";
		Query query = getSessionFactory().openSession().createQuery(hql);
		int count = ((Long) query.uniqueResult()).intValue();
		 
		return count;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
		
}
