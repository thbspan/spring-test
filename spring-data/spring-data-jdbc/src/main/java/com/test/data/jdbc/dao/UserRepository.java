package com.test.data.jdbc.dao;

import com.test.data.jdbc.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    long countByLastName(String lastName);

    List<User> findByLastName(String lastName);
}
