package com.finchool.server.repository;

import com.finchool.server.entities.Achievement;
import com.finchool.server.entities.User;

import java.util.List;

public interface UserRepository {
    void save(User user);
    User findByAndroidId(int id);
    List<Achievement> getUserAchievements(int id);
}
