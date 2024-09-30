package com.finchool.server.controller;

import com.finchool.server.dto.AchievementDto;
import com.finchool.server.dto.AchievementIdDto;
import com.finchool.server.dto.AchievementNameDto;
import com.finchool.server.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/achievement")
public class AchievementController {
    private final AchievementService achievementService;

    @Autowired
    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @GetMapping
    public List<AchievementDto> getAll(){
        return achievementService.findAll();
    }

    @PostMapping
    public void save(@RequestBody AchievementNameDto achievementNameDto){
        achievementService.save(achievementNameDto);
    }

    @DeleteMapping
    public void deleteById(@RequestBody AchievementIdDto achievementIdDto){
        achievementService.deleteById(achievementIdDto);
    }
}
