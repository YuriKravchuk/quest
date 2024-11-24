package com.quest.model.entity;

import com.quest.model.services.GameConfig;

public class Player {
    private int health;
    private boolean hasTreasure;
    private boolean hasWeapon;
    private boolean hasPrinces = false;
    private int x;

    public void setHealth(int health) {
        this.health = health;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    private int y;
    private boolean inTree; // Додайте поле для дерев
    private boolean inCave; // Додайте поле для печери
    private boolean isTalking;
    private String heroIcon;
    private String heroName;

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getHeroIcon() {
        return heroIcon;
    }

    public void setHeroIcon(String heroIcon) {
        this.heroIcon = heroIcon;
    }

    public boolean isHasPrinces() {
        return hasPrinces;
    }

    public void setHasPrinces(boolean hasPrinces) {
        this.hasPrinces = hasPrinces;
    }

    public boolean getIsTalking() {
        return isTalking;
    }

    public void setTalking(boolean talking) {
        isTalking = talking;
    }

    public Player(int startX, int startY) {
        this.health = 100;
        this.x = startX;
        this.y = startY;
        this.inTree = false;
        this.inCave = false;
        this.isTalking = false;
    }

    public int getHealth() {
        return health;
    }

    public void decreaseHealth(int amount) {
        health = Math.max(0, health - amount);
    }

    public void increaseHealth(int amount) {
        health = Math.min(100, health + amount);
    }

    public boolean hasTreasure() {
        return hasTreasure;
    }

    public void setTreasure(boolean hasTreasure) {
        this.hasTreasure = hasTreasure;
    }

    public boolean hasWeapon() {
        return hasWeapon;
    }

    public void setWeapon(boolean hasWeapon) {
        this.hasWeapon = hasWeapon;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isInTree() {
        return inTree;
    }

    public void setInTree(boolean inTree) {
        this.inTree = inTree;
    }

    public boolean isInCave() {
        return inCave;
    }

    public void setInCave(boolean inCave) {
        this.inCave = inCave;
    }

    public void move(String direction) {
        switch (direction) {
            case "up":
                x = Math.max(0, x - 1);
                this.decreaseHealth(1);
                break;
            case "down":
                x = Math.min(GameConfig.getWidth() - 1, x + 1);
                this.decreaseHealth(1);
                break;
            case "left":
                y = Math.max(0, y - 1);
                this.decreaseHealth(1);
                break;
            case "right":
                y = Math.min(GameConfig.getHeight() - 1, y + 1);
                this.decreaseHealth(1);
            break;
        }
    }
}
