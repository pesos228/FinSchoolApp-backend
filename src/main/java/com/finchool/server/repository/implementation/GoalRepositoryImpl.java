package com.finchool.server.repository.implementation;

import com.finchool.server.entities.Goal;
import com.finchool.server.repository.GoalRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;


@Repository
public class GoalRepositoryImpl implements GoalRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Goal goal) {
        if (entityManager.contains(goal)) {
            entityManager.merge(goal);
        } else {
            entityManager.persist(goal);
        }
    }

    @Override
    public Goal findByPhotoUrl(String url) {
        try {
            return entityManager.createQuery("SELECT g FROM Goal g WHERE g.photoUrl = :url", Goal.class)
                    .setParameter("url", url)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Goal findById(int id) {
        try {
            return entityManager.createQuery("SELECT g FROM Goal g WHERE g.id = :id", Goal.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        Goal goal = findById(id);
        if (goal != null){
            entityManager.remove(goal);
        }
    }
}
