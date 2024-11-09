package com.finchool.server.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "article")
public class Article extends BaseEntity{
    private String title;
    private String content;
    private Module module;
    private List<User> users = new ArrayList<>();

    protected Article(){

    }

    public Article(String title, String content, Module module, List<User> users) {
        this.title = title;
        this.content = content;
        this.module = module;
        this.users = users;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    @ManyToMany(mappedBy = "savedArticle")
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
