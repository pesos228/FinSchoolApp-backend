package com.finchool.server.controller;

import com.finchool.server.dto.AchievementDto;
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

    @PostMapping("/{name}")
    public void save(@PathVariable String name){
        achievementService.save(name);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        achievementService.deleteById(id);
    }
}
