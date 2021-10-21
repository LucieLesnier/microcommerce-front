package com.example.demo;



public class Character {
    private String name;
    private int life;

    private int id;

    public Character(String name, int life, int id) {
        this.name =name;
        this.life = life;
        this.id = id;
    }

    public Character() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
