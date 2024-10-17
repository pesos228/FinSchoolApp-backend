package com.finchool.server.service.implementation;

import com.finchool.server.dto.ThemeDto;
import com.finchool.server.dto.ThemeDtoList;
import com.finchool.server.entities.Theme;
import com.finchool.server.exceptions.ThemeNotFoundException;
import com.finchool.server.repository.ThemeRepository;
import com.finchool.server.service.ThemeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ThemeServiceImpl(ThemeRepository themeRepository, ModelMapper modelMapper) {
        this.themeRepository = themeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(ThemeDto theme) {
        themeRepository.save(modelMapper.map(theme, Theme.class));
    }

    @Override
    public void deleteById(int id) {
        Theme theme = themeRepository.findById(id);
        if (theme == null){
            throw new ThemeNotFoundException("Theme with ID: " + id + " not found");
        }
        themeRepository.deleteById(id);
    }

    @Override
    public List<ThemeDtoList> findAll() {
        return themeRepository.findAll().stream()
                .map(theme -> modelMapper.map(theme, ThemeDtoList.class))
                .collect(Collectors.toList());
    }
}
