package com.finchool.server.service;

import com.finchool.server.dto.ArticleDto;
import com.finchool.server.dto.ArticleDtoList;

import java.util.List;

public interface ArticleService {
    void save(ArticleDto articleDto);
    List<ArticleDtoList> findAll();
    ArticleDto findById(int id);
    void deleteById(int id);
    List<ArticleDtoList> findByModuleId(int id);
}
