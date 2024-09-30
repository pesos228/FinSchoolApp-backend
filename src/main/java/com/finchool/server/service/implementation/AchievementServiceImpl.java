package com.finchool.server.service.implementation;

import com.finchool.server.dto.AchievementDto;
import com.finchool.server.dto.AchievementIdDto;
import com.finchool.server.dto.AchievementNameDto;
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
    public void save(AchievementNameDto achievementNameDto){
        Achievement achievement = achievementRepository.findByName(achievementNameDto.getName());
        if (achievement != null){
            throw new AchievementAlreadyExistsException("Achievement "+achievementNameDto.getName()+" already exists");
        }
        System.out.println(achievementNameDto.getName());
        achievementRepository.save(modelMapper.map(achievementNameDto, Achievement.class));
    }

    @Override
    @Transactional
    public void deleteById(AchievementIdDto achievementIdDto) {
        Achievement achievement = achievementRepository.findById(achievementIdDto.getId());
        if (achievement == null){
            throw new AchievementNotFoundException("Achievement "+achievementIdDto.getId()+ " not found");
        }
        achievementRepository.deleteById(achievementIdDto.getId());
    }

    @Override
    public List<AchievementDto> findAll() {
        return achievementRepository.findAll().stream()
                .map(achievement -> modelMapper.map(achievement, AchievementDto.class))
                .collect(Collectors.toList());
    }
}
