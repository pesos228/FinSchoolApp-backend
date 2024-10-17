package com.finchool.server.service.implementation;

import com.finchool.server.dto.*;
import com.finchool.server.entities.Achievement;
import com.finchool.server.entities.Theme;
import com.finchool.server.entities.User;
import com.finchool.server.exceptions.*;
import com.finchool.server.repository.AchievementRepository;
import com.finchool.server.repository.ThemeRepository;
import com.finchool.server.repository.UserRepository;
import com.finchool.server.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AchievementRepository achievementRepository;
    private final ThemeRepository themeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AchievementRepository achievementRepository, ThemeRepository themeRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.achievementRepository = achievementRepository;
        this.themeRepository = themeRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public void save(UserDto userDto) {
        User user = userRepository.findByAndroidId(userDto.getAndroidId());
        if (user != null) {
            user.setLvl(userDto.getLvl());
        } else {
            user = modelMapper.map(userDto, User.class);
        }
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void addAchievement(AddAchievementToUserDto addAchievementToUserDto){
        User user = userRepository.findByAndroidId(addAchievementToUserDto.getAndroidId());
        if (user == null){
            throw new UserNotFoundException("User with android_id "+addAchievementToUserDto.getAndroidId()+" not found");
        }
        Achievement achievement = achievementRepository.findById(addAchievementToUserDto.getAchievementId());
        if (achievement == null){
            throw new AchievementNotFoundException("Achievement with ID "+ addAchievementToUserDto.getAchievementId()+ " not found");
        }

        user.getAchievementsReceived().add(achievement);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void removeAchievement(AddAchievementToUserDto addAchievementToUserDto){
        User user = userRepository.findByAndroidId(addAchievementToUserDto.getAndroidId());
        if (user == null){
            throw new UserNotFoundException("User with android_id "+addAchievementToUserDto.getAndroidId()+" not found");
        }
        Achievement achievement = achievementRepository.findById(addAchievementToUserDto.getAchievementId());
        if (achievement == null){
            throw new AchievementNotFoundException("Achievement with ID "+ addAchievementToUserDto.getAchievementId()+ " not found");
        }

        user.getAchievementsReceived().remove(achievement);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void addThemeToFavorite(ThemeToOrFromFavoriteDto themeToOrFromFavoriteDto) {
        User user = userRepository.findByAndroidId(themeToOrFromFavoriteDto.getAndroidId());
        if (user == null){
            throw new UserNotFoundException("User with android_id "+ themeToOrFromFavoriteDto.getAndroidId()+" not found");
        }
        Theme theme = themeRepository.findById(themeToOrFromFavoriteDto.getThemeId());
        if (theme == null){
            throw new ThemeNotFoundException("Theme with ID: "+ themeToOrFromFavoriteDto.getThemeId() + " not found");
        }
        List<Theme> savedThemes = user.getSavedThemes();
        if (savedThemes.contains(theme)){
            throw new ThemeAlreadySavedException("User with androidId: " + themeToOrFromFavoriteDto.getAndroidId() + " already saved theme with ID: " + themeToOrFromFavoriteDto.getThemeId());
        }
        savedThemes.add(theme);
        user.setSavedThemes(savedThemes);
        userRepository.save(user);

    }

    @Override
    @Transactional
    public void removeThemeFromFavorite(ThemeToOrFromFavoriteDto themeToOrFromFavoriteDto) {
        User user = userRepository.findByAndroidId(themeToOrFromFavoriteDto.getAndroidId());
        if (user == null){
            throw new UserNotFoundException("User with android_id "+ themeToOrFromFavoriteDto.getAndroidId()+" not found");
        }
        Theme theme = themeRepository.findById(themeToOrFromFavoriteDto.getThemeId());
        if (theme == null){
            throw new ThemeNotFoundException("Theme with ID: "+ themeToOrFromFavoriteDto.getThemeId() + " not found");
        }
        List<Theme> savedThemes = user.getSavedThemes();
        if (!savedThemes.contains(theme)){
            throw new ThemeNotSavedException("User with androidId: " + themeToOrFromFavoriteDto.getAndroidId() + " not have theme with ID: " + themeToOrFromFavoriteDto.getThemeId()
            + " in favorite list");
        }
        savedThemes.remove(theme);
        user.setSavedThemes(savedThemes);
        userRepository.save(user);

    }

    @Override
    public UserDto findUserByAndroidId(int id) {
        User user = userRepository.findByAndroidId(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with Android ID: " + id);
        }
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<AchievementNameDto> getUserAchievements(int id) {
        User user = userRepository.findByAndroidId(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with Android ID: " + id);
        }
        return userRepository.getUserAchievements(id).stream()
                .map(achievement -> modelMapper.map(achievement, AchievementNameDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GoalDtoList> getUserGoals(int id) {
        User user = userRepository.findByAndroidId(id);
        if(user == null){
            throw new UserNotFoundException("User not found with Android ID: " +id);
        }
        return userRepository.getUserGoals(id).stream()
                .map(goal -> modelMapper.map(goal, GoalDtoList.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ThemeDtoList> getUserSavedThemes(int id) {
        User user = userRepository.findByAndroidId(id);
        if(user == null){
            throw new UserNotFoundException("User not found with Android ID: " +id);
        }
        return userRepository.getUserSavedThemes(id).stream()
                .map(theme -> modelMapper.map(theme, ThemeDtoList.class))
                .collect(Collectors.toList());
    }

}
