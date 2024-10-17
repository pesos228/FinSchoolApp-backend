package com.finchool.server.service;

import com.finchool.server.dto.AchievementDto;

import java.util.List;

public interface AchievementService {
    void save(String name);
    void deleteById(int id);
    List<AchievementDto> findAll();
}
