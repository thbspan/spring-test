package com.test.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.test.jdbc.domain.User;

@Repository
public class UserRepository {

    private DataSource dataSource;

    public boolean save(User user) {
        return jdbcInsert(user);
    }

    private JdbcTemplate jdbcTemplate;

    private PlatformTransactionManager platformTransactionManager;

    @SuppressWarnings("unused")
	private boolean txJdbcInsert(User user) {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transaction = platformTransactionManager.getTransaction(transactionDefinition);
        jdbcTemplate.execute("INSERT INTO user(name) VALUES (?)", (PreparedStatement ps) -> {
            ps.setString(1, user.getName());
            return ps.executeUpdate() > 0;
        });
        platformTransactionManager.commit(transaction);
        return false;
    }


    private boolean jdbcInsert(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user(name) VALUES (?)")) {
            // 事物
            connection.setAutoCommit(false);
            preparedStatement.setString(1, user.getName());
            boolean result = preparedStatement.executeUpdate() > 0;
            connection.commit();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @SuppressWarnings("unused")
	private boolean jdbcTemplateInsert(User user) {
        return false;
    }

    public List<User> listAll() {
        return jdbcTemplate.query("SELECT id, name FROM user", (resultSet, rowNum) -> {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            return user;
        });
    }

    @Autowired
    public void setPlatformTransactionManager(PlatformTransactionManager platformTransactionManager) {
        this.platformTransactionManager = platformTransactionManager;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
