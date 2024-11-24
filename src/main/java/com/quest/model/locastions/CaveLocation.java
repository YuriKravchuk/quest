package com.quest.model.locastions;

import com.quest.model.entity.Monster;
import com.quest.model.entity.Player;
import com.quest.model.services.InteractionResult;
import com.quest.model.services.ObjectType;

import java.util.Random;

public class CaveLocation extends Location {
    private boolean hasMonster = false;

    public void setHasMonster(boolean hasMonster) {
        this.hasMonster = hasMonster;
    }

    public void setHasTreasure(boolean hasTreasure) {
        this.hasTreasure = hasTreasure;
    }

    private boolean hasTreasure = false;
    private boolean hasWeapon = false;

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    private Monster monster;

    public CaveLocation() {
        this.type = ObjectType.CAVE;
        Random rand = new Random();
        int chance = rand.nextInt(100);

        // 40% шанс на наявність монстра
        if (chance < 40) {
            this.hasMonster = true;
            this.monster = new Monster();
        }
        // 30% шанс на наявність скарбу
        else if (chance < 70) {
            this.hasTreasure = true;
        }
        // 20% шанс на наявність зброї
        else if (chance < 90) {
            this.hasWeapon = true;
        }
    }

    public boolean isHasMonster() {
        return hasMonster;
    }

    public boolean isHasTreasure() {
        return hasTreasure;
    }

    public boolean isHasWeapon() {
        return hasWeapon;
    }

    @Override
    public String getDescription() {
        return "Біля печери";
    }

    public InteractionResult enterCave(Player player) {
        player.setInCave(true);
        if (hasMonster) {
            return InteractionResult.MONSTER_PRESENT;
        } else if (hasTreasure) {
            return takeTreasure(player);
        } else if (hasWeapon) {
            return takeWeapon(player);
        }
        return InteractionResult.NO_MONSTER;
    }

    public InteractionResult fightMonster(Player player) {
        if (hasMonster) {
            // Якщо в гравця є зброя, він завдає 25% пошкодження, якщо немає зброї — 5%
            int damage = player.hasWeapon() ? 25 : 5;
            monster.decreaseHealth(damage);
            player.decreaseHealth(10);
            // Повертаємо результат бою
            if (monster.isAlive()) {
                InteractionResult.TEMP_RESULT.setTextDescription(InteractionResult.HIT_MONSTER.getTextDescription() + monster.getHealth());
                return InteractionResult.TEMP_RESULT.TEMP_RESULT;
            } else {
                this.hasMonster = false;
                if (!player.isHasPrinces()){
                    player.setHasPrinces(true);
                    return InteractionResult.SAVE_PRINCESS;
                } else {
                    return InteractionResult.NO_MONSTER;
                }
            }
        }
        return InteractionResult.NO_MONSTER;
    }

    public InteractionResult leaveCave(Player player) {
        player.setInCave(false);
        return InteractionResult.CAVE_EXITED;
    }

    public InteractionResult fleeFromMonster(Player player) {
        player.setInCave(false);  // Втеча з печери
        player.decreaseHealth(10);  // Здоров'я гравця зменшується на 10% під час втечі
        return InteractionResult.FLEE_SUCCESS;
    }

    // Метод для взяття скарбу
    private InteractionResult takeTreasure(Player player) {
        if (hasTreasure) {
            player.setTreasure(true);
            this.hasTreasure = false;
            return InteractionResult.TREASURE_FOUND;
        }
        return InteractionResult.NO_MONSTER;
    }

    // Метод для взяття зброї
    private InteractionResult takeWeapon(Player player) {
        if (hasWeapon) {
            player.setWeapon(true);
            this.hasWeapon = false;
            return InteractionResult.WEAPON_FOUND;
        }
        return InteractionResult.NO_MONSTER;
    }

}
