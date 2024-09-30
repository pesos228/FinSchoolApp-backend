package com.finchool.server.repository.implementation;

import com.finchool.server.entities.Achievement;
import com.finchool.server.repository.AchievementRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AchievementRepositoryImpl implements AchievementRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void save(Achievement achievement){
        if (entityManager.contains(achievement)) {
            entityManager.merge(achievement);
        } else {
            entityManager.persist(achievement);
        }
    }

    @Override
    public Achievement findByName(String name) {
        try {
            return entityManager.createQuery("SELECT a FROM Achievement a WHERE a.name = :name", Achievement.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Achievement findById(int id) {
        return entityManager.find(Achievement.class, id);
    }

    @Override
    public void deleteById(int id) {
        try {
            Achievement achievement = entityManager.createQuery("SELECT a FROM Achievement a WHERE a.id = :id", Achievement.class)
                    .setParameter("id", id)
                    .getSingleResult();
            entityManager.remove(achievement);
        }catch (NoResultException ignored){

        }
    }

    @Override
    public List<Achievement> findAll() {
        return entityManager.createQuery("SELECT a FROM Achievement a", Achievement.class)
                .getResultList();
    }
}
