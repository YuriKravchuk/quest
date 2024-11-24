package com.quest.model.entity;

public class Monster {
    private int health;

    public Monster() {
        this.health = 100;  // Початкове здоров'я монстра
    }

    public int getHealth() {
        return health;
    }

    public void decreaseHealth(int amount) {
        health = Math.max(0, health - amount);
    }

    public boolean isAlive() {
        return health > 0;
    }
}
