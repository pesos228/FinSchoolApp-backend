package com.finchool.server.repository;

import com.finchool.server.entities.Achievement;

import java.util.List;

public interface AchievementRepository {
    void save(Achievement achievement);
    Achievement findByName(String name);
    Achievement findById(int id);
    void deleteById(int id);
    List<Achievement> findAll();
}
