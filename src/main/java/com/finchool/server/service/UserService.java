package com.finchool.server.service;

import com.finchool.server.dto.AchievementNameDto;
import com.finchool.server.dto.AddAchievementToUserDto;
import com.finchool.server.dto.UserAndroidIdDto;
import com.finchool.server.dto.UserDto;
import com.finchool.server.entities.User;

import java.util.List;

public interface UserService {
    void save(UserDto userDto);
    void addAchievement(AddAchievementToUserDto addAchievementToUserDto);
    void removeAchievement(AddAchievementToUserDto addAchievementToUserDto);
    UserDto findUserDtoByAndroidId(UserAndroidIdDto userAndroidIdDto);
    User findUserByAndroidId(int id);
    List<AchievementNameDto> getUserAchievements(UserAndroidIdDto userAndroidIdDto);
}
