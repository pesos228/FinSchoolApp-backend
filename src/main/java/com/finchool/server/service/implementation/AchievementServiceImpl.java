package com.finchool.server.service.implementation;

import com.finchool.server.dto.AchievementDto;
import com.finchool.server.entities.Achievement;
import com.finchool.server.exceptions.AchievementAlreadyExistsException;
import com.finchool.server.exceptions.AchievementNotFoundException;
import com.finchool.server.repository.AchievementRepository;
import com.finchool.server.service.AchievementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AchievementServiceImpl implements AchievementService {
    private final AchievementRepository achievementRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public AchievementServiceImpl(ModelMapper modelMapper, AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void save(String name){
        Achievement achievement = achievementRepository.findByName(name);
        if (achievement != null){
            throw new AchievementAlreadyExistsException("Achievement "+name+" already exists");
        }
        System.out.println(name);
        achievementRepository.save(modelMapper.map(name, Achievement.class));
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Achievement achievement = achievementRepository.findById(id);
        if (achievement == null){
            throw new AchievementNotFoundException("Achievement "+id+ " not found");
        }
        achievementRepository.deleteById(id);
    }

    @Override
    public List<AchievementDto> findAll() {
        return achievementRepository.findAll().stream()
                .map(achievement -> modelMapper.map(achievement, AchievementDto.class))
                .collect(Collectors.toList());
    }
}
