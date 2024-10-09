package com.finchool.server.controller;

import com.finchool.server.dto.GoalDtoList;
import com.finchool.server.dto.GoalDtoSave;
import com.finchool.server.dto.UserAndroidIdDto;
import com.finchool.server.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goal")
public class GoalController {

    private final GoalService goalService;

    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping
    public void saveGoal(@RequestBody GoalDtoSave goalDto){
        goalService.save(goalDto);
    }

    @PutMapping
    public void updateGoal(@RequestBody GoalDtoList goalDtoList){
        goalService.update(goalDtoList);
    }

    @GetMapping
    public List<GoalDtoList> getGoal(@RequestBody UserAndroidIdDto userAndroidIdDto){
        return goalService.findByAndroidId(userAndroidIdDto.getAndroidId());
    }
}
