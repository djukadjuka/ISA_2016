package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		UserBean user = new UserBean();
		
		user.setId(rs.getInt("id"));
		user.setEmail(rs.getString("email"));
		user.setFirstName(rs.getString("firstName"));
		user.setLastName(rs.getString("lastName"));
		user.setPassword(rs.getString("password"));
		user.setProfilePicture(rs.getString("profilePicture"));
		user.setUsername(rs.getString("username"));
		
		return user;
	}
	
	
	
}
