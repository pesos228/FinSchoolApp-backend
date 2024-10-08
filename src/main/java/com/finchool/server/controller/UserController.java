package com.finchool.server.controller;

import com.finchool.server.dto.AchievementNameDto;
import com.finchool.server.dto.AddAchievementToUserDto;
import com.finchool.server.dto.UserAndroidIdDto;
import com.finchool.server.dto.UserDto;
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

    @GetMapping
    public UserDto getUser(@RequestBody UserAndroidIdDto userAndroidIdDto){
        return userService.findUserDtoByAndroidId(userAndroidIdDto);
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

    @GetMapping("/achievements")
    public List<AchievementNameDto> getUserAchievements(@RequestBody UserAndroidIdDto userAndroidIdDto){
        return userService.getUserAchievements(userAndroidIdDto);
    }
}
