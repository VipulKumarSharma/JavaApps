package jdbcapp.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class SimpleJdbcDAOImpl extends JdbcDaoSupport {

	/* DAO Support Classes have simply datasource, templates, etc variables 
	 * and their getters & setters
	 */
	
	public int getTriangleCount_usingJdbcTemplateSupportClass() {
		String sql = "SELECT COUNT(*) FROM TRIANGLE";
		int count = this.getJdbcTemplate().queryForObject(sql, Integer.class);
		
		return count;
	}
}
