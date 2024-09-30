package com.finchool.server.service.implementation;

import com.finchool.server.dto.AddAchievementToUserDto;
import com.finchool.server.dto.UserAndroidIdDto;
import com.finchool.server.dto.UserDto;
import com.finchool.server.entities.Achievement;
import com.finchool.server.entities.User;
import com.finchool.server.exceptions.AchievementNotFoundException;
import com.finchool.server.exceptions.UserNotFoundException;
import com.finchool.server.repository.AchievementRepository;
import com.finchool.server.repository.UserRepository;
import com.finchool.server.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AchievementRepository achievementRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AchievementRepository achievementRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.achievementRepository = achievementRepository;
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
        Achievement achievement = achievementRepository.findById(addAchievementToUserDto.getAndroidId());
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
        Achievement achievement = achievementRepository.findById(addAchievementToUserDto.getAndroidId());
        if (achievement == null){
            throw new AchievementNotFoundException("Achievement with ID "+ addAchievementToUserDto.getAchievementId()+ " not found");
        }

        user.getAchievementsReceived().remove(achievement);
        userRepository.save(user);
    }

    @Override
    public UserDto findByAndroidId(UserAndroidIdDto userAndroidIdDto) {
        User user = userRepository.findByAndroidId(userAndroidIdDto.getAndroidId());
        if (user == null) {
            throw new UserNotFoundException("User not found with Android ID: " + userAndroidIdDto.getAndroidId());
        }
        return modelMapper.map(user, UserDto.class);
    }

}
