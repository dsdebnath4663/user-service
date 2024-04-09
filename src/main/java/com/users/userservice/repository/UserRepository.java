package com.users.userservice.repository;

import com.users.userservice.UserRowMapper;
import com.users.userservice.domain.User;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;


    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<User> findAll() {
        String sql = "SELECT * FROM Users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }


    public List<User> findAll(int page, int size) {
        int offset = (page - 1) * size; // Calculate offset based on page and size
        String sql = "SELECT * FROM (SELECT u.*, ROW_NUMBER() OVER (ORDER BY id) AS row_num FROM Users u) WHERE row_num BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new Object[]{offset + 1, offset + size}, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> findByLastNameLike(String lastNamePattern) {
        String sql = "SELECT * FROM Users WHERE last_name LIKE ?";
        String wildcardPattern = "%" + lastNamePattern + "%";
        return jdbcTemplate.query(sql, new Object[]{wildcardPattern}, new UserRowMapper());
    }

    public List<User> findUsersByAgeRange(int minAge, int maxAge) {
        String sql = "SELECT * FROM Users WHERE age BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new Object[]{minAge, maxAge}, new UserRowMapper());
    }

    public User findById(Long id) {
        String sql = "SELECT * FROM Users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserRowMapper());
    }


    public User save(User user) {
        String sql = "INSERT INTO Users (id, firstName, lastName, email, gender, avatar, domain, available) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getGender(), user.getAvatar(), user.getDomain(), user.getAvailable());
        return user;
    }


    public void update(User user) {
        String sql = "UPDATE Users SET firstName = ?, lastName = ?, email = ?, gender = ?, " +
                "avatar = ?, domain = ?, available = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getGender(),
                user.getAvatar(), user.getDomain(), user.getAvailable(), user.getId());
    }


    public void deleteById(Long id) {
        String sql = "DELETE FROM Users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


    public List<User> saveAll(List<User> users) {
        String sql = "INSERT INTO Users (id, firstName, lastName, email, gender, avatar, domain, available) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                User user = users.get(i);
                ps.setLong(1, user.getId());
                ps.setString(2, user.getFirstName());
                ps.setString(3, user.getLastName());
                ps.setString(4, user.getEmail());
                ps.setString(5, user.getGender());
                ps.setString(6, user.getAvatar());
                ps.setString(7, user.getDomain());
                ps.setString(8, user.getAvailable());
            }

            @Override
            public int getBatchSize() {
                return users.size();
            }
        });
        return users;


    }

    // UserRowMapper class (for mapping ResultSet to User objects) goes here
}

