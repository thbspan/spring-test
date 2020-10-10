package com.test.xml.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.test.xml.entity.User;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int index) throws SQLException {
        return new User(resultSet.getInt("id"), resultSet.getString("name"),
                resultSet.getInt("age"), resultSet.getString("sex"));
    }
}
