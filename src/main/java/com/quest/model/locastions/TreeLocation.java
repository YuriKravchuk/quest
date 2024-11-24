package com.quest.model.locastions;

import com.quest.model.services.InteractionResult;
import com.quest.model.services.ObjectType;
import com.quest.model.entity.Player;

public class TreeLocation extends Location {
    private final int exitX; // координати виходу з лісу
    private final int exitY;
    private String exitDirection;

    public TreeLocation(int exitX, int exitY) {
        this.type = ObjectType.TREE;
        this.exitX = exitX;
        this.exitY = exitY;
    }

    @Override
    public String getDescription() {
        return "Біля дерева.";
    }

    public InteractionResult climbTree(Player player) {
        player.setInTree(true);
        if (isExitVisible(player)) {
            setExitDirection(player);
            InteractionResult.TEMP_RESULT.setTextDescription(InteractionResult.SEE_EXIT.getTextDescription() + exitDirection);
            return InteractionResult.TEMP_RESULT;
        }
        return InteractionResult.CLIMB_SUCCESS;
    }

    public InteractionResult descendTree(Player player) {
        player.setInTree(false);
        return InteractionResult.DESCEND_SUCCESS;
    }

    public InteractionResult jumpFromTree(Player player) {
        player.setInTree(false);
        player.decreaseHealth(20);
        return InteractionResult.JUMP_FORM_TREE_SUCCESS;
    }

    // Метод для перевірки видимості виходу
    private boolean isExitVisible(Player player) {
        int playerX = player.getX();
        int playerY = player.getY();

        int distance = Math.abs(playerX - exitX) + Math.abs(playerY - exitY);

        return distance <= 5;
    }

    // Метод для визначення напрямку до виходу
    private void setExitDirection(Player player) {
        int playerX = player.getX();
        int playerY = player.getY();
        if (exitY > playerY && exitX < playerX){
            exitDirection = "↗️";
        }
        else if (exitY > playerY && exitX > playerX) {
            exitDirection = "↘️";
        }
        else if (exitY < playerY && exitX > playerX) {
            exitDirection = "↙️";
        }
        else if (exitY < playerY && exitX < playerX) {
            exitDirection = "↖️";
        }
        else if (exitY > playerY) {
            exitDirection =  "➡️";
        } else if (exitY < playerY) {
            exitDirection = "⬅️";
        } else if (exitX > playerX) {
            exitDirection =  "⬇️";
        } else if (exitX < playerX) {
            exitDirection = "⬆️";
        }
    }
}
