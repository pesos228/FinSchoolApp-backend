package com.finchool.server.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "achievement")
public class Achievement extends BaseEntity{
    private String name;
    private List<User> users = new ArrayList<>();

    protected Achievement(){}

    public Achievement(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    @Column(nullable = false, unique = true, length = 250, name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "achievementsReceived")
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
