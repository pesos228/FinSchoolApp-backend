package com.finchool.server.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "article")
public class Article extends BaseEntity{
    private String title;
    private String content;
    private Theme themeId;

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
    @JoinColumn(name = "theme_id", nullable = false)
    public Theme getTheme() {
        return themeId;
    }

    public void setTheme(Theme theme) {
        this.themeId = theme;
    }
}
