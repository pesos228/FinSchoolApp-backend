package com.finchool.server.service;

import com.finchool.server.dto.ModuleDto;
import com.finchool.server.dto.ModuleDtoList;

import java.util.List;

public interface ModuleService {
    void save(ModuleDto moduleDto);
    void deleteById(int id);
    List<ModuleDtoList> findAll();
}
