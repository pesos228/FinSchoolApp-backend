package com.finchool.server.service.implementation;

import com.finchool.server.dto.*;
import com.finchool.server.entities.Achievement;
import com.finchool.server.entities.Article;
import com.finchool.server.entities.User;
import com.finchool.server.exceptions.*;
import com.finchool.server.repository.AchievementRepository;
import com.finchool.server.repository.ArticleRepository;
import com.finchool.server.repository.UserRepository;
import com.finchool.server.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AchievementRepository achievementRepository;
    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AchievementRepository achievementRepository , ArticleRepository articleRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.achievementRepository = achievementRepository;
        this.articleRepository = articleRepository;
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
        Achievement achievement = achievementRepository.findById(addAchievementToUserDto.getAchievementId());
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
        Achievement achievement = achievementRepository.findById(addAchievementToUserDto.getAchievementId());
        if (achievement == null){
            throw new AchievementNotFoundException("Achievement with ID "+ addAchievementToUserDto.getAchievementId()+ " not found");
        }

        user.getAchievementsReceived().remove(achievement);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void addArticleToFavorite(ArticleToOrFromFavoriteDto articleToOrFromFavoriteDto) {
        User user = userRepository.findByAndroidId(articleToOrFromFavoriteDto.getAndroidId());
        if (user == null){
            throw new UserNotFoundException("User with android_id "+ articleToOrFromFavoriteDto.getAndroidId()+" not found");
        }
        Article article = articleRepository.findById(articleToOrFromFavoriteDto.getArticleId());
        if (article == null){
            throw new ArticleNotFoundException("Article with ID: "+ articleToOrFromFavoriteDto.getArticleId() + " not found");
        }
        List<Article> savedArticles = user.getSavedArticle();
        if (savedArticles.contains(article)){
            throw new ArticleAlreadySavedException("User with androidId: " + articleToOrFromFavoriteDto.getAndroidId() + " already saved article with ID: " + articleToOrFromFavoriteDto.getArticleId());
        }
        savedArticles.add(article);
        user.setSavedArticle(savedArticles);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void removeArticleFromFavorite(ArticleToOrFromFavoriteDto articleToOrFromFavoriteDto) {
        User user = userRepository.findByAndroidId(articleToOrFromFavoriteDto.getAndroidId());
        if (user == null){
            throw new UserNotFoundException("User with android_id "+ articleToOrFromFavoriteDto.getAndroidId()+" not found");
        }
        Article article = articleRepository.findById(articleToOrFromFavoriteDto.getArticleId());
        if (article == null){
            throw new ArticleNotFoundException("Article with ID: "+ articleToOrFromFavoriteDto.getArticleId() + " not found");
        }
        List<Article> savedArticles = user.getSavedArticle();
        if (!savedArticles.contains(article)){
            throw new ArticleAlreadySavedException("User with androidId: " + articleToOrFromFavoriteDto.getAndroidId() + " not have article with ID: " + articleToOrFromFavoriteDto.getArticleId()
                    + " in favorite list");
        }
        savedArticles.remove(article);
        user.setSavedArticle(savedArticles);
        userRepository.save(user);
    }

    @Override
    public UserDto findUserByAndroidId(int id) {
        User user = userRepository.findByAndroidId(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with Android ID: " + id);
        }
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<AchievementNameDto> getUserAchievements(int id) {
        User user = userRepository.findByAndroidId(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with Android ID: " + id);
        }
        return userRepository.getUserAchievements(id).stream()
                .map(achievement -> modelMapper.map(achievement, AchievementNameDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GoalDtoList> getUserGoals(int id) {
        User user = userRepository.findByAndroidId(id);
        if(user == null){
            throw new UserNotFoundException("User not found with Android ID: " +id);
        }
        return userRepository.getUserGoals(id).stream()
                .map(goal -> modelMapper.map(goal, GoalDtoList.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> getUserSavedArticles(int id) {
        User user = userRepository.findByAndroidId(id);
        if(user == null){
            throw new UserNotFoundException("User not found with Android ID: " +id);
        }
        return userRepository.getUserSavedArticles(id).stream()
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .collect(Collectors.toList());
    }

}
