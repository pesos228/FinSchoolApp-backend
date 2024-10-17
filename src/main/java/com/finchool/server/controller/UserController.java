package com.finchool.server.controller;

import com.finchool.server.dto.*;
import com.finchool.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable int id){
        return userService.findUserByAndroidId(id);
    }

    @PostMapping
    public void save(@RequestBody UserDto userDto){
        userService.save(userDto);
    }

    @PostMapping("/add-achievement")
    public void addAchievement(@RequestBody AddAchievementToUserDto addAchievementToUserDto){
        userService.addAchievement(addAchievementToUserDto);
    }

    @PostMapping("/remove-achievement")
    public void removeAchievement(@RequestBody AddAchievementToUserDto addAchievementToUserDto){
        userService.removeAchievement(addAchievementToUserDto);
    }

    @GetMapping("/achievements/{id}")
    public List<AchievementNameDto> getUserAchievements(@PathVariable int id){
        return userService.getUserAchievements(id);
    }

    @PostMapping("/themes/add")
    public void addThemeInFavoriteList(@RequestBody ThemeToOrFromFavoriteDto themeToOrFromFavoriteDto){
        userService.addThemeToFavorite(themeToOrFromFavoriteDto);
    }

    @PostMapping("/themes/remove")
    public void removeThemeFromFavoriteList(@RequestBody ThemeToOrFromFavoriteDto themeToOrFromFavoriteDto){
        userService.removeThemeFromFavorite(themeToOrFromFavoriteDto);
    }

    @GetMapping("/themes/{id}")
    public List<ThemeDtoList> getSavedTheme(@PathVariable int id){
        return userService.getUserSavedThemes(id);
    }

    @GetMapping("/goals/{id}")
    public List<GoalDtoList> getGoal(@PathVariable int id){
        return userService.getUserGoals(id);
    }

}
