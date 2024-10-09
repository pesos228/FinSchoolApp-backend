package com.finchool.server.repository;

import com.finchool.server.entities.Goal;

import java.util.List;

public interface GoalRepository {
    void save(Goal goal);
    List<Goal> findByAndroidId(int id);
    Goal findByPhotoUrl(String url);
    Goal findById(int id);
}
