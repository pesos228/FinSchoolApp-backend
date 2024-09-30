package com.finchool.server.repository.implementation;

import com.finchool.server.entities.User;
import com.finchool.server.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user){
        if (entityManager.contains(user)) {
            entityManager.merge(user);
        } else {
            entityManager.persist(user);
        }
    }

    @Override
    public User findByAndroidId(int id) {
        try {
            return entityManager.createQuery("SELECT u FROM User u WHERE u.androidId = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
