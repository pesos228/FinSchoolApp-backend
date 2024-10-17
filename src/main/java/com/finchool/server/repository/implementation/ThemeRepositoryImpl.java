package com.finchool.server.repository.implementation;

import com.finchool.server.entities.Theme;
import com.finchool.server.repository.ThemeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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
    public Theme findById(int id) {
        try {
            return entityManager.createQuery("SELECT t FROM Theme t WHERE t.id = :id", Theme.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
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