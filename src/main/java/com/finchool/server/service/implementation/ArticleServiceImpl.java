package com.finchool.server.service.implementation;

import com.finchool.server.dto.ArticleDto;
import com.finchool.server.dto.ArticleDtoList;
import com.finchool.server.entities.Article;
import com.finchool.server.entities.Theme;
import com.finchool.server.exceptions.ArticleNotFoundException;
import com.finchool.server.exceptions.ThemeNotFoundException;
import com.finchool.server.repository.ArticleRepository;
import com.finchool.server.repository.ThemeRepository;
import com.finchool.server.service.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ThemeRepository themeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, ThemeRepository themeRepository, ModelMapper modelMapper) {
        this.articleRepository = articleRepository;
        this.themeRepository = themeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void save(ArticleDto articleDto) {
        Theme theme = themeRepository.findById(articleDto.getThemeId());
        if (theme == null){
            throw new ThemeNotFoundException("Theme with ID: " + articleDto.getThemeId() + " not found");
        }
        Article article = modelMapper.map(articleDto, Article.class);
        article.setTheme(theme);
        articleRepository.save(article);
    }

    @Override
    public List<ArticleDtoList> findAll() {
        return articleRepository.findAll().stream()
                .map(article -> modelMapper.map(article, ArticleDtoList.class))
                .collect(Collectors.toList());
    }

    @Override
    public ArticleDto findById(int id) {
        Article article = articleRepository.findById(id);
        if (article == null){
            throw new ArticleNotFoundException("Article with ID: " + id + " not found");
        }
        return modelMapper.map(article, ArticleDto.class);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Article article = articleRepository.findById(id);
        if (article == null){
            throw new ArticleNotFoundException("Article with ID: " + id + " not found");
        }
        articleRepository.deleteById(id);
    }
}
