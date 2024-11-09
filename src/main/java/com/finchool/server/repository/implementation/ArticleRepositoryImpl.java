package com.finchool.server.repository.implementation;

import com.finchool.server.entities.Article;
import com.finchool.server.repository.ArticleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Article article) {
        if (entityManager.contains(article)){
            entityManager.merge(article);
        }else{
            entityManager.persist(article);
        }
    }

    @Override
    public List<Article> findAll() {
        return entityManager.createQuery("SELECT a FROM Article a", Article.class)
                .getResultList();
    }

    @Override
    public Article findById(int id) {
        try {
            return entityManager.createQuery("SELECT a FROM Article a WHERE a.id = :id", Article.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        Article article = findById(id);
        if (article != null){
            entityManager.remove(article);
        }
    }

    @Override
    public List<Article> findByModuleId(int id) {
        return entityManager.createQuery("SELECT a FROM Article a JOIN a.module m WHERE m.id = :id", Article.class)
                .setParameter("id", id)
                .getResultList();
    }
}
