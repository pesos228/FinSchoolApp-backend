package com.finchool.server.repository;

import com.finchool.server.entities.Achievement;
import com.finchool.server.entities.Goal;
import com.finchool.server.entities.Theme;
import com.finchool.server.entities.User;

import java.util.List;

public interface UserRepository {
    void save(User user);
    User findByAndroidId(int id);
    List<Achievement> getUserAchievements(int id);
    List<Goal> getUserGoals(int id);
    List<Theme> getUserSavedThemes(int id);
}
