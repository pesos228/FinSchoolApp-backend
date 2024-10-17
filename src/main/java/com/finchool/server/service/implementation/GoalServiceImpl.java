package com.finchool.server.service.implementation;

import com.finchool.server.dto.GoalDtoList;
import com.finchool.server.dto.GoalDtoSave;
import com.finchool.server.entities.Goal;
import com.finchool.server.entities.User;
import com.finchool.server.exceptions.GoalNotFoundException;
import com.finchool.server.exceptions.GoalUrlAlreadyExistsException;
import com.finchool.server.exceptions.UserNotFoundException;
import com.finchool.server.repository.GoalRepository;
import com.finchool.server.repository.UserRepository;
import com.finchool.server.service.GoalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GoalServiceImpl(GoalRepository goalRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.goalRepository = goalRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void save(GoalDtoSave goalDto) {
        User user = userRepository.findByAndroidId(goalDto.getAndroidId());
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

    //Вообще как будто плохо использовать репозиторий в не его сервисе. Повторяем логику... Возможно для этого надо использовать фасад, чтобы не было повторов у нас
    // но чёт это запарно. Может потом сделаем, но как будто для маленького нашего сервочка это уже излишок, зажрётся, обжора

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

    @Override
    public void deleteById(int id) {
        Goal goal = goalRepository.findById(id);
        if (goal == null){
            throw new GoalNotFoundException("Goal with ID: "+ id + " not found");
        }
        goalRepository.deleteById(id);
    }


}
