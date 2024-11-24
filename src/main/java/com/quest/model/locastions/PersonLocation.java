package com.quest.model.locastions;

import com.quest.model.services.InteractionResult;
import com.quest.model.services.ObjectType;
import com.quest.model.entity.Player;

import java.util.Random;

public class PersonLocation extends Location {

    private boolean hasWeapon;  // Чи має людина зброю

    public void setKnowsExit(boolean knowsExit) {
        this.knowsExit = knowsExit;
    }

    private boolean knowsExit;  // Чи знає людина вихід
    private int exitX;  // Координати виходу
    private int exitY;
    private String exitDirection;

    public PersonLocation(int exitX, int exitY) {
        this.type = ObjectType.PERSON;
        Random rand = new Random();
        this.hasWeapon = rand.nextBoolean();  // Рандомно визначаємо, чи є зброя
        this.knowsExit = rand.nextBoolean();  // Рандомно визначаємо, чи знає вихід
        this.exitX = exitX;
        this.exitY = exitY;
    }

    @Override
    public String getDescription() {
        return "Біля мандрівника.";
    }

    public boolean isHasWeapon() {
        return hasWeapon;
    }

    public boolean isKnowsExit() {
        return knowsExit;
    }

    public InteractionResult talkWithHuman (Player player) {
        player.setTalking(true);
        if (knowsExit && !hasWeapon) {
            return InteractionResult.HUMAN_KNOW_EXIT;
        }
        else if (hasWeapon && !knowsExit) {
            return InteractionResult.HUMAN_HAVE_WEAPON;
        } else if (knowsExit && hasWeapon) {
            InteractionResult.TEMP_RESULT.setTextDescription(InteractionResult.HUMAN_KNOW_EXIT.getTextDescription() + " " + InteractionResult.HUMAN_HAVE_WEAPON.getTextDescription());
            return InteractionResult.TEMP_RESULT;
        }
        return InteractionResult.HUMAN_EMPTY;
    }

    public InteractionResult stopTalking(Player player) {
        player.setTalking(false);
        return InteractionResult.STOP_TALKING;
    }

    // Метод для придбання зброї, якщо у героя є скарб
    public InteractionResult tradeWeapon(Player player) {
        if (player.hasTreasure() && !player.hasWeapon()) {
            player.setWeapon(true);
            player.setTreasure(false);
            this.hasWeapon = false;
            return InteractionResult.BUY_WEAPON;
        } else if (player.hasWeapon()) {
            return InteractionResult.HAVE_WEAPON_ALREADY; // Вже має зброю
        } else {
            return InteractionResult.NO_MONEY;
        }
    }

    public InteractionResult giveExitDirection(Player player) {
        if (knowsExit) {
            setExitDirection(player);
            InteractionResult.TEMP_RESULT.setTextDescription(InteractionResult.HUMAN_GIVE_EXIT_DIRECTION.getTextDescription() + getExitDirection());
            return InteractionResult.TEMP_RESULT;
        } else {
            return InteractionResult.HUMAN_DONT_KNOW_EXIT;
        }
    }

    // Метод для визначення напрямку до виходу
    private void setExitDirection(Player player) {

        int playerX = player.getX();
        int playerY = player.getY();

        if (exitY > playerY && exitX < playerX) {
            exitDirection = "↗️";
        } else if (exitY > playerY && exitX > playerX) {
            exitDirection = "↘️";
        } else if (exitY < playerY && exitX > playerX) {
            exitDirection = "↙️";
        } else if (exitY < playerY && exitX < playerX) {
            exitDirection = "↖️";
        } else if (exitY > playerY) {
            exitDirection = "➡️";
        } else if (exitY < playerY) {
            exitDirection = "⬅️";
        } else if (exitX > playerX) {
            exitDirection = "⬇️";
        } else if (exitX < playerX) {
            exitDirection = "⬆️";
        }
    }

    public String getExitDirection() {
        return exitDirection;
    }

}