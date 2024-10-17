package com.finchool.server.config;

import com.finchool.server.dto.AchievementNameDto;
import com.finchool.server.dto.ArticleDto;
import com.finchool.server.dto.GoalDtoSave;
import com.finchool.server.dto.UserDto;
import com.finchool.server.entities.Achievement;
import com.finchool.server.entities.Article;
import com.finchool.server.entities.Goal;
import com.finchool.server.entities.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<UserDto, User>() {
            @Override
            protected void configure() {
                skip().setId(null);
                map().setLvl(source.getLvl());
                map().setAndroidId(source.getAndroidId());
                map().setName(source.getName());
                skip().setAchievementsReceived(null);
                skip().setSavedThemes(null);
            }
        });

        modelMapper.addMappings(new PropertyMap<AchievementNameDto, Achievement>() {
            @Override
            protected void configure() {
                skip().setId(null);
                map().setName(source.getName());
                skip().setUsers(null);
            }
        });

        modelMapper.addMappings(new PropertyMap<GoalDtoSave, Goal>() {
            @Override
            protected void configure() {
                skip().setId(null);
                map().setName(source.getName());
                map().setTargetAmount(source.getTargetAmount());
                map().setCurrentAmount(source.getCurrentAmount());
                map().setPhotoUrl(source.getPhotoUrl());
            }
        });

        modelMapper.addMappings(new PropertyMap<ArticleDto, Article>() {
            @Override
            protected void configure(){
                skip().setId(null);
                map().setContent(source.getContent());
                map().setTitle(source.getTitle());
            }
        });
        return modelMapper;
    }
}
