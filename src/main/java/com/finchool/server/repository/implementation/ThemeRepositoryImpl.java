package com.finchool.server.repository.implementation;

import com.finchool.server.entities.Theme;
import com.finchool.server.repository.ThemeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ThemeRepositoryImpl implements ThemeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Theme theme) {
        if (entityManager.contains(theme)) {
            entityManager.merge(theme);
        }
        else {
            entityManager.persist(theme);
        }
    }

    @Override
    public void deleteById(int id) {
        Theme theme = entityManager.find(Theme.class, id);
        entityManager.remove(theme);
    }

    @Override
    public List<Theme> findAll() {
        return entityManager.createQuery("from Theme", Theme.class).getResultList();
    }
}