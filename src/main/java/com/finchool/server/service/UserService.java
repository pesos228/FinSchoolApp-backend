package com.finchool.server.service;

import com.finchool.server.dto.AddAchievementToUserDto;
import com.finchool.server.dto.UserAndroidIdDto;
import com.finchool.server.dto.UserDto;

public interface UserService {
    void save(UserDto userDto);
    void addAchievement(AddAchievementToUserDto addAchievementToUserDto);
    void removeAchievement(AddAchievementToUserDto addAchievementToUserDto);
    UserDto findByAndroidId(UserAndroidIdDto userAndroidIdDto);

}
