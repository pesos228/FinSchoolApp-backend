package com.finchool.server.repository;

import com.finchool.server.entities.User;

public interface UserRepository {
    void save(User user);
    User findByAndroidId(int id);
}
