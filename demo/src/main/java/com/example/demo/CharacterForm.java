package com.example.demo;

public class CharacterForm {

    private String name;
    private int life;
    private int id;

    public CharacterForm() {
    }

    public CharacterForm(String name, int life, int id) {
        this.name = name;
        this.life = life;
        this.id = id;
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


