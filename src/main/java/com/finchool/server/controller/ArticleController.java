package com.finchool.server.controller;

import com.finchool.server.dto.ArticleDto;
import com.finchool.server.dto.ArticleDtoList;
import com.finchool.server.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<ArticleDtoList> findAll(){
        return articleService.findAll();
    }

    @PostMapping
    public void save(@RequestBody ArticleDto articleDto){
        articleService.save(articleDto);
    }

    @GetMapping("/{id}")
    public ArticleDto findById(@PathVariable int id){
        return articleService.findById(id);
    }

    @GetMapping("/module/{id}")
    public List<ArticleDtoList> findByModuleId(@PathVariable int id){return articleService.findByModuleId(id);}

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        articleService.deleteById(id);
    }
}
