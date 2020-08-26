package com.test.mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.entity.ShadowUser;
import com.test.repository.ShadowUserRepository;

public interface ShadowUserMapper extends ShadowUserRepository {
    @Override
    default List<ShadowUser> selectAll() throws SQLException {
        List<ShadowUser> result = new ArrayList<>();
        result.addAll(selectAllByShadow(true));
        result.addAll(selectAllByShadow(false));
        return result;
    }

    List<ShadowUser> selectAllByShadow(boolean shadow) throws SQLException;
}
