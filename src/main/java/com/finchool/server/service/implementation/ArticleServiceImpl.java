package com.finchool.server.service.implementation;

import com.finchool.server.dto.ArticleDto;
import com.finchool.server.dto.ArticleDtoList;
import com.finchool.server.entities.Article;
import com.finchool.server.entities.Module;
import com.finchool.server.exceptions.ArticleNotFoundException;
import com.finchool.server.exceptions.ModuleNotFoundException;
import com.finchool.server.repository.ArticleRepository;
import com.finchool.server.repository.ModuleRepository;
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
    private final ModuleRepository moduleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, ModuleRepository moduleRepository, ModelMapper modelMapper) {
        this.articleRepository = articleRepository;
        this.moduleRepository = moduleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void save(ArticleDto articleDto) {
        Module module = moduleRepository.findById(articleDto.getModuleId());
        if (module == null){
            throw new ModuleNotFoundException("Module with ID: " + articleDto.getModuleId() + " not found");
        }
        Article article = modelMapper.map(articleDto, Article.class);
        article.setModule(module);
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

    @Override
    public List<ArticleDtoList> findByModuleId(int id) {
        Module module = moduleRepository.findById(id);
        if (module == null){
            throw new ModuleNotFoundException("Module with ID: " + id + " not found");
        }
        return articleRepository.findByModuleId(id).stream()
                .map(article -> modelMapper.map(article, ArticleDtoList.class))
                .collect(Collectors.toList());
    }
}
