package blog.test;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.Test;

import blog.config.DB;

public class DBTest {
	
	@Test
	public void 디비연결() {
		Connection conn = DB.getConnection();
		assertNotNull(conn);
	}

}
