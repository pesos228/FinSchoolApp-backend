package com.finchool.server.repository;

import com.finchool.server.entities.Article;

import java.util.List;

public interface ArticleRepository {
    void save(Article article);
    List<Article> findAll();
    Article findById(int id);
    void deleteById(int id);
}
