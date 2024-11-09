package com.finchool.server.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "module")
public class Module extends BaseEntity{
    private String name;
    private String description;
    private List<Article> articles = new ArrayList<>();
    protected Module(){}

    public Module(String name, String description, List<Article> articles) {
        this.name = name;
        this.description = description;
        this.articles = articles;
    }

    @Column(nullable = false, unique = true, name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false, name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "module", cascade = CascadeType.REMOVE)
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
