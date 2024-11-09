package com.finchool.server.repository.implementation;

import com.finchool.server.entities.Module;
import com.finchool.server.repository.ModuleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ModuleRepositoryImpl implements ModuleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Module module) {
        if (entityManager.contains(module)) {
            entityManager.merge(module);
        }
        else {
            entityManager.persist(module);
        }
    }

    @Override
    public Module findById(int id) {
        try {
            return entityManager.createQuery("SELECT m FROM Module m WHERE m.id = :id", Module.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        Module module = entityManager.find(Module.class, id);
        entityManager.remove(module);
    }

    @Override
    public List<Module> findAll() {
        return entityManager.createQuery("from Module", Module.class).getResultList();
    }
}