package com.finchool.server.service;

import com.finchool.server.entities.Theme;

import java.util.List;

public interface ThemeService {
    void save(Theme theme);
    void deleteById(int id);
    List<Theme> findAll();
}
