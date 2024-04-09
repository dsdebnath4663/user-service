package com.users.userservice;

import com.users.userservice.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setEmail(rs.getString("email"));
        user.setGender(rs.getString("gender"));
        user.setAvatar(rs.getString("avatar"));
        user.setDomain(rs.getString("domain"));
        user.setAvailable(rs.getString("available")); // Assuming available is CHAR(1)

        return user;
    }
}
