package com.finchool.server.repository;

import com.finchool.server.entities.Goal;

public interface GoalRepository {
    void save(Goal goal);
    Goal findByPhotoUrl(String url);
    Goal findById(int id);
    void deleteById(int id);
}
