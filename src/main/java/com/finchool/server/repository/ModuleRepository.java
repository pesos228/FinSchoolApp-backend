package com.finchool.server.repository;

import com.finchool.server.entities.Module;

import java.util.List;

public interface ModuleRepository {
    void save(Module module);
    Module findById(int id);
    void deleteById(int id);
    List<Module> findAll();
}
