package com.finchool.server.service;

import com.finchool.server.dto.*;

import java.util.List;

public interface UserService {
    void save(UserDto userDto);
    void addAchievement(AddAchievementToUserDto addAchievementToUserDto);
    void removeAchievement(AddAchievementToUserDto addAchievementToUserDto);
    void addArticleToFavorite(ArticleToOrFromFavoriteDto articleToOrFromFavoriteDto);
    void removeArticleFromFavorite(ArticleToOrFromFavoriteDto articleToOrFromFavoriteDto);
    UserDto findUserByAndroidId(int id);
    List<AchievementNameDto> getUserAchievements(int id);
    List<GoalDtoList> getUserGoals(int id);
    List<ArticleDto> getUserSavedArticles(int id);
}
