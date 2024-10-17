package com.finchool.server.service;

import com.finchool.server.dto.ThemeDto;
import com.finchool.server.dto.ThemeDtoList;

import java.util.List;

public interface ThemeService {
    void save(ThemeDto themeDto);
    void deleteById(int id);
    List<ThemeDtoList> findAll();
}
