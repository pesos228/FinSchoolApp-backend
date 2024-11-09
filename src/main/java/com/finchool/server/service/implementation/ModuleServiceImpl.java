package com.finchool.server.service.implementation;

import com.finchool.server.dto.ModuleDto;
import com.finchool.server.dto.ModuleDtoList;
import com.finchool.server.entities.Module;
import com.finchool.server.exceptions.ModuleNameOrDescriptionNotFoundException;
import com.finchool.server.exceptions.ModuleNotFoundException;
import com.finchool.server.repository.ModuleRepository;
import com.finchool.server.service.ModuleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ModuleServiceImpl(ModuleRepository moduleRepository, ModelMapper modelMapper) {
        this.moduleRepository = moduleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void save(ModuleDto moduleDto) {
        if (moduleDto == null) {
            throw new IllegalArgumentException("ModuleDto cannot be null");
        }
        if (moduleDto.getName() == null) {
            throw new ModuleNameOrDescriptionNotFoundException("Module name cannot be empty");
        }
        if (moduleDto.getDescription() == null) {
            throw new ModuleNameOrDescriptionNotFoundException("Module description cannot be empty");
        }
        moduleRepository.save(modelMapper.map(moduleDto, Module.class));
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Module module = moduleRepository.findById(id);
        if (module == null){
            throw new ModuleNotFoundException("Module with ID: " + id + " not found");
        }
        moduleRepository.deleteById(id);
    }

    @Override
    public List<ModuleDtoList> findAll() {
        return moduleRepository.findAll().stream()
                .map(module -> modelMapper.map(module, ModuleDtoList.class))
                .collect(Collectors.toList());
    }
}
