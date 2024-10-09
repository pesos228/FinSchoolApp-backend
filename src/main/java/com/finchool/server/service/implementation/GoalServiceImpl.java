package com.finchool.server.service.implementation;

import com.finchool.server.dto.GoalDtoList;
import com.finchool.server.dto.GoalDtoSave;
import com.finchool.server.dto.UserAndroidIdDto;
import com.finchool.server.dto.UserDto;
import com.finchool.server.entities.Goal;
import com.finchool.server.entities.User;
import com.finchool.server.exceptions.GoalNotFoundException;
import com.finchool.server.exceptions.GoalUrlAlreadyExistsException;
import com.finchool.server.exceptions.UserNotFoundException;
import com.finchool.server.repository.GoalRepository;
import com.finchool.server.service.GoalService;
import com.finchool.server.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public GoalServiceImpl(GoalRepository goalRepository, UserService userService, ModelMapper modelMapper) {
        this.goalRepository = goalRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void save(GoalDtoSave goalDto) {
        User user = userService.findUserByAndroidId(goalDto.getAndroidId());
        if (user == null) {
            throw new UserNotFoundException("User not found with Android ID: " + goalDto.getAndroidId());
        }
        Goal goal = goalRepository.findByPhotoUrl(goalDto.getPhotoUrl());
        if (goal != null){
            throw new GoalUrlAlreadyExistsException("Goal with URL: " + goalDto.getPhotoUrl() + " already exists");
        }

        Goal newGoal = modelMapper.map(goalDto, Goal.class);
        newGoal.getUsers().add(user);

        goalRepository.save(newGoal);

    }

    @Override
    public List<GoalDtoList> findByAndroidId(int id) {
        UserAndroidIdDto userAndroidIdDto = new UserAndroidIdDto(id);
        UserDto user = userService.findUserDtoByAndroidId(userAndroidIdDto);
        if(user == null){
            throw new UserNotFoundException("User not found with Android ID: " +id);
        }
        return goalRepository.findByAndroidId(id).stream()
                .map(goal -> modelMapper.map(goal, GoalDtoList.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void update(GoalDtoList goalDtoList) {
        Goal goal = goalRepository.findById(goalDtoList.getId());
        if (goal == null){
            throw new GoalNotFoundException("Goal with ID: "+ goalDtoList.getId() + " not found");
        }
        modelMapper.map(goalDtoList, goal);
        goalRepository.save(goal);
    }


}
