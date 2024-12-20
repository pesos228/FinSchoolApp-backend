package com.finchool.server.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private int androidId;
    private String name;
    private int lvl = 1;
    private List<Article> savedArticle = new ArrayList<>();
    private List<Achievement> achievementsReceived = new ArrayList<>();
    private List<Goal> goals = new ArrayList<>();
    protected User(){}

    public User(int androidId, String name, int lvl, List<Article> savedArticle, List<Achievement> achievementsReceived, List<Goal> goals) {
        this.androidId = androidId;
        this.name = name;
        this.lvl = lvl;
        this.savedArticle = savedArticle;
        this.achievementsReceived = achievementsReceived;
        this.goals = goals;
    }

    @ManyToMany(mappedBy = "users")
    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    @Column(nullable = false, unique = true, name = "android_id")
    public int getAndroidId() {
        return androidId;
    }

    public void setAndroidId(int androidId) {
        this.androidId = androidId;
    }

    @Column(nullable = false, length = 12, name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false, name = "lvl")
    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    @ManyToMany
    @JoinTable(
            name = "saved_articles",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    public List<Article> getSavedArticle() {
        return savedArticle;
    }

    public void setSavedArticle(List<Article> savedArticle) {
        this.savedArticle = savedArticle;
    }

    @ManyToMany
    @JoinTable(
            name = "user_achievement",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "achievement_id")
    )
    public List<Achievement> getAchievementsReceived() {
        return achievementsReceived;
    }

    public void setAchievementsReceived(List<Achievement> achievementsReceived) {
        this.achievementsReceived = achievementsReceived;
    }
}
