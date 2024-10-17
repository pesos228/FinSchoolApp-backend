package com.finchool.server.controller;

import com.finchool.server.dto.ThemeDto;
import com.finchool.server.dto.ThemeDtoList;
import com.finchool.server.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theme")
public class ThemeController {
    private final ThemeService themeService;

    @Autowired
    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping
    public List<ThemeDtoList> findAll(){
        return themeService.findAll();
    }

    @PostMapping
    public void save(@RequestBody ThemeDto themeDto){
        themeService.save(themeDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTheme(@PathVariable int id){
        themeService.deleteById(id);
    }
}
