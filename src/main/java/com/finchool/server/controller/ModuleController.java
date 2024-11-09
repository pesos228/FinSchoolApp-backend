package com.finchool.server.controller;

import com.finchool.server.dto.ModuleDto;
import com.finchool.server.dto.ModuleDtoList;
import com.finchool.server.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/module")
public class ModuleController {
    private final ModuleService moduleService;

    @Autowired
    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @GetMapping
    public List<ModuleDtoList> findAll(){
        return moduleService.findAll();
    }

    @PostMapping
    public void save(@RequestBody ModuleDto moduleDto){
        moduleService.save(moduleDto);
    }

    @DeleteMapping("/{id}")
    public void deleteModule(@PathVariable int id){
        moduleService.deleteById(id);
    }
}
