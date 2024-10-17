package com.finchool.server.service;

import com.finchool.server.dto.AchievementNameDto;
import com.finchool.server.dto.AddAchievementToUserDto;
import com.finchool.server.dto.GoalDtoList;
import com.finchool.server.dto.UserDto;
import java.util.List;

public interface UserService {
    void save(UserDto userDto);
    void addAchievement(AddAchievementToUserDto addAchievementToUserDto);
    void removeAchievement(AddAchievementToUserDto addAchievementToUserDto);
    UserDto findUserByAndroidId(int id);
    List<AchievementNameDto> getUserAchievements(int id);
    List<GoalDtoList> getUserGoals(int id);
}
