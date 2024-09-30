package com.finchool.server.service;

import com.finchool.server.dto.AchievementDto;
import com.finchool.server.dto.AchievementIdDto;
import com.finchool.server.dto.AchievementNameDto;

import java.util.List;

public interface AchievementService {
    void save(AchievementNameDto achievementNameDto);
    void deleteById(AchievementIdDto achievementIdDto);
    List<AchievementDto> findAll();
}
