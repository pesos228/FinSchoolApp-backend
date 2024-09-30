package com.finchool.server.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "goal_users")
public class Goal extends BaseEntity{
    private User user;
    private String name;
    private BigDecimal targetAmount;
    private BigDecimal currentAmount = BigDecimal.ZERO;
    private String photoUrl;

    protected Goal(){}

    public Goal(User user, String name, BigDecimal targetAmount, BigDecimal currentAmount, String photoUrl) {
        this.user = user;
        this.name = name;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.photoUrl = photoUrl;
    }

    @ManyToOne
    @JoinColumn(name = "users_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(nullable = true, name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = true, precision = 11, scale = 2, name = "target_amount")
    public BigDecimal getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(BigDecimal targetAmount) {
        this.targetAmount = targetAmount;
    }

    @Column(nullable = true, precision = 11, scale = 2, name = "current_amount")
    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }

    @Column(nullable = true, unique = true, name = "photo_url")
    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
