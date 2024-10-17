package com.finchool.server.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theme")
public class Theme extends BaseEntity{
    private String name;
    private List<User> users = new ArrayList<>();
    private List<Article> articles = new ArrayList<>();
    protected Theme(){}

    public Theme(String name, List<User> users, List<Article> articles) {
        this.name = name;
        this.users = users;
        this.articles = articles;
    }

    @Column(nullable = false, unique = true, name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "savedThemes")
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @OneToMany(mappedBy = "theme")
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
