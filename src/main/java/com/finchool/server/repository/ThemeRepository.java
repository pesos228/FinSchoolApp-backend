package com.finchool.server.repository;

import com.finchool.server.entities.Theme;

import java.util.List;

public interface ThemeRepository {
    void save(Theme theme);
    void deleteById(int id);
    List<Theme> findAll();
}
